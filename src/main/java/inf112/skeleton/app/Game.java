package inf112.skeleton.app;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Boards.Board.Board;
import inf112.skeleton.app.Boards.Board.SquareType;
import inf112.skeleton.app.Boards.Board.TraceResult;
import inf112.skeleton.app.Player.Piece;
import inf112.skeleton.app.Player.Player;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Game {
    private Board board;
    private ArrayList<Player> players;
    private boolean game_over;
    private Consumer<String> broadcast;

    public Game(Board board, int numPlayers) {
        this(board, numPlayers, false);
    }

    // set noSprite to true if you aren't going to actually draw the sprites, for tests and server use, as it causes an exception
    public Game(Board board, int numPlayers, boolean noSprite) {
        this.board = board;
        players = new ArrayList<>(numPlayers);
        for (int i = 0; i < numPlayers; i++) {
            if (noSprite) players.add(i, new Player(i, new Vector2(i * 2 + 2, 0)));
            else {
                int j = (i % 4) + 1;
                String path = "textures/piece" + j + "/$dir.png";
                players.add(i, new Player(path, i, new Vector2(i * 2 + 2, 0)));
            }
        }
    }

    void supplyBroadcaster(Consumer<String> broadcast) {
        this.broadcast = broadcast;
    }

    // draws the board and all the players to the specified batch
    void draw(SpriteBatch batch) {
        board.draw(batch, 1);
        for (Player player : players) {
            player.piece.draw(batch, 1);
        }
    }

    // Attempts to move the specified player in the specified direction
    // Returns true if the player reaches the requested destination
    private boolean stepPlayer(Player player, Direction direction) {
        Vector2 destination = new Vector2(player.piece.getPosition()).add(Direction.toVector2(direction));
        TraceResult trace = board.traceLine(player.piece.getPosition(), direction); // Shoots a "laser" to check for walls
        if (trace.getPositions().contains(destination)) {
            player.piece.setPosition(destination);
            return true; // Reached the goal
        } else if (!trace.hitWall()) // Did not reach destination, but was not stopped.
        {
            player.print("left the map.");
            player.respawn();
            return false; // The intended destination is unreachable.
        }

        return false; // Did not move
    }

    // Receives a command (two strings from a player) and performs actions requested by that command
    void handleCommand(Command command) {
        if (command.command.equals("KEY")) {
            int key = Integer.parseInt(command.value);
            Player player = players.get(command.id);
            Piece piece = player.piece;

            switch (key) {
                case Input.Keys.DOWN:
                    stepPlayer(player, Direction.rotateCW(Direction.rotateCW(piece.getDirection())));
                    break;
                case Input.Keys.UP:
                    stepPlayer(player, piece.getDirection());
                    break;
                case Input.Keys.LEFT:
                    piece.rotateCCW();
                    break;
                case Input.Keys.RIGHT:
                    piece.rotateCW();
                    break;
                case Input.Keys.SPACE:
                    tick(player);
                    break;
                    //For testing game.move()
                case Input.Keys.ENTER:
                    move(player, "Move back"); //player.takeCard().toString());
                    break;
                    //For testing game.move(player, "Again");
                case Input.Keys.R:
                    move(player, "Again"); //player.takeCard().toString());
                    break;

            }
        } else if (command.command.equals("CARDS")) {
            System.out.println("SERVER RECEIVED CARDS");
            for (String card : command.value.split(",")) {
                players.get(command.id).print("chose card: " + card);
                //players.get(command.id).program.add(card);
            }
        } else if (command.command.equals("END")) {
            game_over = true;
        }
    }

    // applies stage hazards and player lasers to the specified player
    private void tick(Player player) {
        int i = players.indexOf(player);
        Piece piece = player.piece;

        int damage = board.getLasers(piece.getPosition());
        for (Player other : players) {
            if (other == player) continue;

            TraceResult playerLaser = board.traceLine(other.piece.getPosition(), other.piece.getDirection());
            if (playerLaser.getPositions().contains(player.piece.getPosition())) damage += 1;
        }
        if (damage > 0) {
            player.print("got hit for " + damage + " damage.");
        }

        if (board.getSquareTypes(piece.getPosition()).contains(SquareType.HOLE)) {
            player.print("fell in a hole.");
            player.respawn();
        }
        //repair
        if (board.getSquareTypes(piece.getPosition()).contains(SquareType.REPAIR)) {
            player.print("got repaired.");
            //TODO: something specified by repair
        }
        //double repair
        if (board.getSquareTypes(piece.getPosition()).contains(SquareType.DOUBLE_REPAIR)) {
            player.print("got double repaired.");
            //TODO: Do something specified by double repair
        }
        //rotate clockwise
        if (board.getSquareTypes(piece.getPosition()).contains(SquareType.ROTATE_CW)) {
            player.print("rotated right.");
            piece.rotateCW();
        }
        //rotate counterclockwise
        if (board.getSquareTypes(piece.getPosition()).contains(SquareType.ROTATE_CCW)) {
            player.print("rotated left.");
            piece.rotateCCW();
        }
        //conveyors
        Vector2 conveyor = getConveyor(player);
        if (conveyor != null) conveyorBelt(player, conveyor);
    }

    // returns a vector representing the type of conveyor belt the player is on, and null if it isn't on any
    // it's a directional vector, except it has a magnitude of 2 if it's a double conveyor
    private Vector2 getConveyor(Player player) {
        Piece piece = player.piece;
        //conveyors
        if (board.getSquareTypes(piece.getPosition()).contains(SquareType.CONVEYOR_SOUTH)) return new Vector2(0, -1);
        else if (board.getSquareTypes(piece.getPosition()).contains(SquareType.CONVEYOR_NORTH))
            return new Vector2(0, 1);
        else if (board.getSquareTypes(piece.getPosition()).contains(SquareType.CONVEYOR_WEST)) return new Vector2(1, 0);
        else if (board.getSquareTypes(piece.getPosition()).contains(SquareType.CONVEYOR_EAST))
            return new Vector2(-1, 0);

        //double conveyors
        if (board.getSquareTypes(piece.getPosition()).contains(SquareType.DOUBLE_CONVEYOR_SOUTH))
            return new Vector2(0, -2);
        else if (board.getSquareTypes(piece.getPosition()).contains(SquareType.DOUBLE_CONVEYOR_NORTH))
            return new Vector2(0, 2);
        else if (board.getSquareTypes(piece.getPosition()).contains(SquareType.DOUBLE_CONVEYOR_WEST))
            return new Vector2(2, 0);
        else if (board.getSquareTypes(piece.getPosition()).contains(SquareType.DOUBLE_CONVEYOR_EAST))
            return new Vector2(-2, 0);

        return null;
    }

    // returns the total state of the board as a string, so that it can be communicated back to the clients
    // exclusively used by the server
    String getState() {
        if (game_over) return "State=Over\n";
        StringBuilder state = new StringBuilder("State=");

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            state.append(i);
            state.append("/");
            state.append(player.piece.getPosition());
            state.append("/");
            state.append(Direction.toInt(player.piece.getDirection()));
            state.append(";");
        }
        state.append("\n");

        return state.toString();
    }

    // accepts a string representing the total state of the board, and applies it to all the sprites
    // exclusively used by the client
    void setState(String state) {
        state = state.split("=")[1];
        for (String playerState : state.split(";")) {
            int id = Integer.parseInt(playerState.split("/")[0]);
            Player player = players.get(id);

            int direction = Integer.parseInt(playerState.split("/")[2]);
            player.piece.setDirection(Direction.fromInt(direction));

            String posString = playerState.split("/")[1].replace("(", "").replace(")", "");
            float x = Float.parseFloat(posString.split(",")[0]);
            float y = Float.parseFloat(posString.split(",")[1]);
            player.piece.setPosition(new Vector2(x, y));
        }
    }

    // given a player and a directional vector as returned by the getConveyor function, determines the number of step
    // and transforms the direction into a Direction type, so that it can be used by the recursive function
    private void conveyorBelt(Player player, Vector2 vel) {
        int steps = (int) vel.len(); // Need to store this before the vector is normalized.
        conveyorBelt(player, Direction.fromVector2(vel.nor()), steps);
    }

    // recursively moves a player along a series of belts changing direction as needed until it either runs out of
    // "steps", aka moves the player as much as it should be, or the player ends up not standing on a conveyor belt
    private void conveyorBelt(Player player, Direction direction, int steps) {
        if (stepPlayer(player, direction)) {
            Vector2 conveyor = getConveyor(player);
            if (conveyor != null) {
                Vector2 dir1 = Direction.toVector2(direction);
                Vector2 dir2 = conveyor.nor();

                int ang = (int) Math.round(Math.atan2(dir1.x - dir2.x, dir1.y - dir2.y) * (180 / Math.PI));
                if (ang == 45 || 180 + ang == 45) player.piece.rotateCW();
                else if (ang == -45) player.piece.rotateCCW();
                if (steps > 1) conveyorBelt(player, Direction.fromVector2(conveyor.nor()), steps - 1);
            }
        }
    }

    //test after player.program is implemented into game
    public void move(Player player, String card) {
        Piece piece = player.piece;

        switch (card) {
            case "Move 1":
                stepPlayer(player, piece.getDirection());
                break;
            case "Move 2":
                stepPlayer(player, piece.getDirection());
                stepPlayer(player, piece.getDirection());
                break;
            case "Move 3":
                stepPlayer(player, piece.getDirection());
                stepPlayer(player, piece.getDirection());
                stepPlayer(player, piece.getDirection());
                break;
            case "Right turn":
                piece.rotateCW();
                break;
            case "Left turn":
                piece.rotateCCW();
                break;
            case "U turn":
                stepPlayer(player, Direction.rotateCW(Direction.rotateCW(piece.getDirection())));
                break;
            case "Move back":
                stepPlayer(player, Direction.rotateCW(Direction.rotateCW(piece.getDirection())));
                stepPlayer(player, piece.getDirection());
                stepPlayer(player, Direction.rotateCW(Direction.rotateCW(piece.getDirection())));
                break;
            case "Again":
                //card = player.lastCard().toString();
                // test this after player.program is implemented into game
                //move(player, card);
                break;
        }

        //lastMove();
    }
}
