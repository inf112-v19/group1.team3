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

public class Game {
    private Board board;
    private ArrayList<Player> players;

    public Game(Board board, int numPlayers) {
        this(board, numPlayers, false);
    }

    public Game(Board board, int numPlayers, boolean noSprite) {
        this.board = board;
        players = new ArrayList<>(numPlayers);
        for (int i = 0; i < numPlayers; i++) {
            if (noSprite) players.add(i, new Player(i, new Vector2(i * 2 + 2, 0)));
            else players.add(i, new Player("textures/piece1/$dir.png", i, new Vector2(i * 2 + 2, 0)));
        }
    }

    void draw(SpriteBatch batch) {
        board.draw(batch, 1);
        for (Player player : players) {
            player.piece.draw(batch, 1);
        }
    }

    // Returns true if the player reaches the requested destination
    private boolean stepPlayer(Player player, Direction direction) {
        Vector2 destination = new Vector2(player.piece.getPosition()).add(Direction.toVector2(direction));
        TraceResult trace = board.traceLine(player.piece.getPosition(), direction);
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
            }
        }
    }

    private void tick(Player player) {
        int i = players.indexOf(player);
        Piece piece = player.piece;

        int damage = board.getLasers(piece.getPosition());
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

    String getState() {
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

    private void conveyorBelt(Player player, Vector2 vel) {
        int steps = (int) vel.len(); // Need to store this before the vector is normalized.
        conveyorBelt(player, Direction.fromVector2(vel.nor()), steps);
    }

    private void conveyorBelt(Player player, Direction direction, int steps) {
        Piece piece = player.piece;
        if (stepPlayer(player, direction) && steps > 1) {
            Vector2 conveyor = getConveyor(player);
            if (conveyor != null) conveyorBelt(player, Direction.fromVector2(conveyor.nor()), steps - 1);
        }
    }
}
