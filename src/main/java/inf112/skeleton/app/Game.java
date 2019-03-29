package inf112.skeleton.app;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public void handleInput(Input input)
    {
        for(int i = 0; i < players.size(); i++)
        {
            Player player = players.get(i);
            Piece piece = player.piece;
            try
            {
                if(input.isKeyJustPressed(Input.Keys.DOWN))
                {
                    TraceResult trace = board.traceLine(piece.getPosition(), Direction.rotateCW(Direction.rotateCW(piece.getDirection())));
                    if(trace.getPositions().contains(piece.getBackward()))
                    {
                        piece.setPosition(piece.getBackward());
                    }
                    else if(!trace.hitWall())
                    {
                        System.out.println("Player " + i + " left the map.");
                        piece.setPosition(i*2+2, 0);
                    }
                }
                if(input.isKeyJustPressed(Input.Keys.UP))
                {
                    TraceResult trace = board.traceLine(piece.getPosition(), piece.getDirection());
                    if(trace.getPositions().contains(piece.getForward()))
                    {
                        piece.setPosition(piece.getForward());
                    }
                    else if(!trace.hitWall())
                    {
                        System.out.println("Player " + i + " left the map.");
                        piece.setPosition(i*2+2, 0);
                    }
                }
                if(input.isKeyJustPressed(Input.Keys.LEFT)) piece.rotateCCW();
                if(input.isKeyJustPressed(Input.Keys.RIGHT)) piece.rotateCW();
            }
            catch(IllegalArgumentException e)
            {
                System.err.println("Tried to move out of bounds");
            }
        }

        if(input.isKeyJustPressed(Input.Keys.SPACE))
        {
            for(int i = 0; i < players.size(); i++)
            {
                Piece piece = players.get(i).piece;
                int damage = board.getLasers(piece.getPosition());
                if (damage > 0)
                {
                    System.out.println("Player " + (i+1) + " got hit for " + damage + " damage.");
                }

                if (board.getSquareTypes(piece.getPosition()).contains(SquareType.HOLE))
                {
                    System.out.println("Player " + (i+1) + " fell in a hole.");
                    piece.setPosition(i*2+2, 0);
                }
            }
        }
    }

    // inf112.skeleton.app.Game step by step
    
    // New game
    // Chooce a name:
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



    public void reSpawnPlayer() {


    }

    public void newRound() {



    }




}
