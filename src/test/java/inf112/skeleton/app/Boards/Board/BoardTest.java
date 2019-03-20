package inf112.skeleton.app.Boards.Board;

import inf112.skeleton.app.Direction;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class BoardTest {
    private int height = ThreadLocalRandom.current().nextInt(2, 20);
    private int width = ThreadLocalRandom.current().nextInt(2, 20);

    @Test
    public void addInfo() {
        Board board = new Board(null, width, height);
        assertTrue(board.getSquareTypes(0, 0).isEmpty());
        board.addSquareType(0, 0, SquareType.WALL_EAST);
        assertTrue(board.getSquareTypes(0, 0).contains(SquareType.WALL_EAST));
        board.addSquareType(0, 0, SquareType.WALL_WEST);
        assertTrue(board.getSquareTypes(0, 0).contains(SquareType.WALL_WEST));
        assertFalse(board.getSquareTypes(0, 0).isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addOutOfBoundsX()
    {
        Board board = new Board(null, width, height);
        board.addSquareType(width+1, 0, SquareType.WALL_EAST);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addOutOfBoundsY()
    {
        Board board = new Board(null, width, height);
        board.addSquareType(0, height+1, SquareType.WALL_EAST);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addOutOfBoundsNegX()
    {
        Board board = new Board(null, width, height);
        board.addSquareType(-1, 0, SquareType.WALL_EAST);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addOutOfBoundsNegY()
    {
        Board board = new Board(null, width, height);
        board.addSquareType(0, -1, SquareType.WALL_EAST);
    }

    @Test
    public void addGetInBoundsAll()
    {
        Board board = new Board(null, width, height);
        for (int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                board.addSquareType(x, y, SquareType.WALL_EAST);
            }
        }

        for (int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                assertTrue(board.getSquareTypes(x, y).contains(SquareType.WALL_EAST));
            }
        }
    }

    @Test
    public void addInLine()
    {
        // Add in a line manually
        Board board = new Board(null, width, height);
        int y = ThreadLocalRandom.current().nextInt(0, height);
        for(int x = 0; x < width-1; x++)
        {
            board.addSquareType(x, y, SquareType.CONVEYOR_NORTH);
        }

        // Add in a line with function
        Board board2 = new Board(null, width, height);
        board2.addInLine(0, y, Direction.EAST, width - 1, SquareType.CONVEYOR_NORTH);

        // Check that boards have identical content
        for(int x = 0; x < width; x++)
        {
            for(y = 0; y < height; y++)
            {
                assertEquals(board.getSquareTypes(x, y), board2.getSquareTypes(x, y));
            }
        }
    }

    @Test
    public void testLaser()
    {
        Board board = new Board(null, 20, height);
        board.addLaser(0, 0, Direction.EAST, 1);

        // Check that laser propagates
        assertEquals(board.getLasers(19, 0), 1);

        // Check that laser gets blocked
        board.addSquareType(10, 0, SquareType.WALL_EAST);
        assertEquals(board.getLasers(19, 0), 0);
        assertEquals(board.getLasers(10, 0), 1);
        assertEquals(board.getLasers(11, 0), 0);

        board.addSquareType(10, 0, SquareType.WALL_WEST);
        assertEquals(board.getLasers(10, 0), 0);
    }
}
