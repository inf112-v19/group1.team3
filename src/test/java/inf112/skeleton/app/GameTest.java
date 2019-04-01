package inf112.skeleton.app;

import com.badlogic.gdx.Input;
import inf112.skeleton.app.Boards.Board.Board;
import org.junit.Test;


import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class GameTest {
    private int height = ThreadLocalRandom.current().nextInt(2, 20);
    private int width = ThreadLocalRandom.current().nextInt(2, 20);

    @Test
    public void handleInputTest (){
        Board board = new Board(null, width, height);
        Game game = new Game(board, 1);



    }
}