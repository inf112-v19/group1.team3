package inf112.skeleton.app;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sun.org.apache.bcel.internal.generic.SWITCH;
import inf112.skeleton.app.Boards.Board.Board;
import inf112.skeleton.app.Boards.Board.SquareType;
import inf112.skeleton.app.Boards.Board.TraceResult;
import inf112.skeleton.app.Boards.ChopShop;
import inf112.skeleton.app.Player.Piece;
import inf112.skeleton.app.Player.Player;
import inf112.skeleton.app.Player.Player;

import java.util.ArrayList;

public class Game
{
    private Board board;
    private ArrayList<Player> players;

    public Game(Board board, int numPlayers)
    {
        this(board, numPlayers, false);
    }

    public Game(Board board, int numPlayers, boolean noSprite)
    {
        this.board = board;
        players = new ArrayList<>(numPlayers);
        for(int i = 0; i < numPlayers; i++)
        {
            if(noSprite) players.add(i, new Player());
            else players.add(i, new Player("textures/piece1/$dir.png"));
            players.get(i).piece.setPosition(i*2+2, 0);
        }
    }

    public int getNumPlayers() { return players.size(); }

    public void draw(SpriteBatch batch)
    {
        board.draw(batch, 1);
        for (Player player : players)
        {
            player.piece.draw(batch, 1);
        }
    }

    public void handleCommand(Command command)
    {
        if (!command.command.equals("KEY")) return;
        int key = Integer.parseInt(command.value);
        Player player = players.get(command.id);
        Piece piece = player.piece;
        try
        {
            if(key == Input.Keys.DOWN)
            {
                TraceResult trace = board.traceLine(piece.getPosition(), Direction.rotateCW(Direction.rotateCW(piece.getDirection())));
                if(trace.getPositions().contains(piece.getBackward()))
                {
                    piece.setPosition(piece.getBackward());
                }
                else if(!trace.hitWall())
                {
                    System.out.println("Player " + command.id + " left the map.");
                    piece.setPosition(command.id*2+2, 0);
                }
            }
            if(key == Input.Keys.UP)
            {
                TraceResult trace = board.traceLine(piece.getPosition(), piece.getDirection());
                if(trace.getPositions().contains(piece.getForward()))
                {
                    piece.setPosition(piece.getForward());
                }
                else if(!trace.hitWall())
                {
                    System.out.println("Player " + command.id + " left the map.");
                    piece.setPosition(command.id*2+2, 0);
                }
            }
            if(key == Input.Keys.LEFT) piece.rotateCCW();
            if(key == Input.Keys.RIGHT) piece.rotateCW();
        }
        catch(IllegalArgumentException e)
        {
            System.err.println("Tried to move out of bounds");
        }


        if(key == Input.Keys.SPACE) {
            for (int i = 0; i < players.size(); i++) {
                piece = players.get(i).piece;
                int damage = board.getLasers(piece.getPosition());
                if (damage > 0) {
                    System.out.println("Player " + (i + 1) + " got hit for " + damage + " damage.");
                }
                //if player falls in hole, set player position to starting position
                if (board.getSquareTypes(piece.getPosition()).contains(SquareType.HOLE)) {
                    System.out.println("Player " + (i + 1) + " fell in a hole.");
                    piece.setPosition(i * 2 + 2, 0);
                }
                //repair
                if (board.getSquareTypes(piece.getPosition()).contains(SquareType.REPAIR)) {
                    System.out.println("Player " + (i + 1) + " got repaired.");
                    //TODO: something specified by repair
                }
                //double repair
                if (board.getSquareTypes(piece.getPosition()).contains(SquareType.DOUBLE_REPAIR)) {
                    System.out.println("Player " + (i + 1) + " got double repaired.");
                    //TODO: Do something specified by double repair
                }
                //rotate clockwise
                if (board.getSquareTypes(piece.getPosition()).contains(SquareType.ROTATE_CW)) {
                    System.out.println("Player " + (i + 1) + " rotated right.");
                    piece.rotateCW();
                }
                //rotate counterclockwise
                if (board.getSquareTypes(piece.getPosition()).contains(SquareType.ROTATE_CCW)) {
                    System.out.println("Player " + (i + 1) + " rotated left.");
                    piece.rotateCCW();
                }
                //move if conveyor south
                if (board.getSquareTypes(piece.getPosition()).contains(SquareType.CONVEYOR_SOUTH)) {
                    //check if new position is out of bounds
                    if(piece.getY()-1 < 0) {
                        leftMap(piece, i);
                    }
                    else {
                        conveyorBelt(piece, Direction.SOUTH, i, 1 );
                        continue;
                    }
                }
                //move if conveyor north
                if (board.getSquareTypes(piece.getPosition()).contains(SquareType.CONVEYOR_NORTH)) {
                    //check if new position is out of bounds
                    if(piece.getY()+1 > 11) { //ideally use board height, but can't access from board.getHeight()
                        leftMap(piece, i);
                    }
                    else {
                        conveyorBelt(piece, Direction.NORTH, i, 1 );
                        continue;
                    }
                }
                //move if conveyor west
                if (board.getSquareTypes(piece.getPosition()).contains(SquareType.CONVEYOR_WEST)) {
                    //check if new position is out of bounds
                    if(piece.getX()-1 < 0) {
                        leftMap(piece, i);
                    }
                    else {
                        conveyorBelt(piece, Direction.WEST, i, 1 );
                        continue;
                    }
                }
                //move if conveyor east
                if (board.getSquareTypes(piece.getPosition()).contains(SquareType.CONVEYOR_EAST)) {
                    //check if new position is out of bounds
                    if(piece.getX() > 11) { //ideally use board width, but can't access from board.getWidth()
                        leftMap(piece, i);
                    }
                    else {
                        conveyorBelt(piece, Direction.EAST, i, 1 );
                        continue;
                    }
                }
                //move if double conveyor south
                if (board.getSquareTypes(piece.getPosition()).contains(SquareType.DOUBLE_CONVEYOR_SOUTH)) {
                    //check if new position is out of bounds
                    if(piece.getY()-2 < 0)
                    {
                        leftMap(piece, i);
                    }
                    else {
                        conveyorBelt(piece, Direction.SOUTH, i, 2 );
                        continue;
                    }
                }
                //move if double conveyor north
                if (board.getSquareTypes(piece.getPosition()).contains(SquareType.DOUBLE_CONVEYOR_NORTH)) {
                    //check if new position is out of bounds
                    if(piece.getY()+2 > 11) { //ideally use board height, but can't access from board.getHeight()
                        leftMap(piece, i);
                    }
                    else {
                        conveyorBelt(piece, Direction.NORTH, i, 2 );
                        continue;
                    }
                }
                //move if double conveyor west
                if (board.getSquareTypes(piece.getPosition()).contains(SquareType.DOUBLE_CONVEYOR_WEST)) {
                    //check if new position is out of bounds
                    if(piece.getX()-2 < 0) {
                        leftMap(piece, i);
                    }
                    else {
                        conveyorBelt(piece, Direction.WEST, i, 2 );
                        continue;
                    }
                }
                //move if double conveyor east
                if (board.getSquareTypes(piece.getPosition()).contains(SquareType.DOUBLE_CONVEYOR_EAST)) {
                    //check if new position is out of bounds
                    if(piece.getX() > 11) { //ideally use board width, but can't access from board.getWidth()
                        leftMap(piece, i);
                    }
                    else {
                        conveyorBelt(piece, Direction.EAST, i, 2 );
                        continue;
                    }
                }
            }
        }
    }

    public String getState()
    {
        StringBuilder state = new StringBuilder("State=");

        for (int i = 0; i < players.size(); i++)
        {
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

    public void setState(String state)
    {
        state = state.split("=")[1];
        for (String playerState : state.split(";"))
        {
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

    // inf112.skeleton.app.Game step by step
    
    // New game
    // Choose a name:
    // New Round
    // New Phase
    // deal 9 cards
    // choose 5 cards
    // confirm, 5 cards
    // for each card, moveRobot()
    // check for collisions after each moveRobot()
    // lasers etc will be active after each phase



    /*  Loop logic

        inf112.skeleton.app.Game {

            Round {

                Phase {

                }

            }
        }

     */




    public void chooseCards () {



    }


    public void newPhase () {


    }





    public void inputFromUser() {


        //something


    }


    public void inputFromCPU() {


    }

    public void moveRobot() {

        //full movement, partly movement, no movement


    }


    //move piece to starting position when leaving the map
    public void leftMap(Piece piece, int i) {
        System.out.println("Player " + (i + 1) + " left the map.");
        piece.setPosition(i*2+2, 0);
    }

    //move piece when entering conveyor belt
    public void conveyorBelt(Piece piece, Direction direction, int i, int steps) {
        switch (direction) {
            case NORTH:
                System.out.println("Player " + (i + 1) + " moved " + steps + " north");
                piece.setPosition(piece.getX(), piece.getY() + steps);
                break;
            case SOUTH:
                System.out.println("Player " + (i + 1) + " moved " + steps + " south");
                piece.setPosition(piece.getX(), piece.getY() - steps);
                break;
            case WEST:
                System.out.println("Player " + (i + 1) + " moved " + steps + " west");
                piece.setPosition(piece.getX() - steps, piece.getY());
                break;
            case EAST:
                System.out.println("Player " + (i + 1) + " moved " + steps + " east");
                piece.setPosition(piece.getX() + steps, piece.getY());
                break;
        }
    }

    public void newRound() {


    }




}
