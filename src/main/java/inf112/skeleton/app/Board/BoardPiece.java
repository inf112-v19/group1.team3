package inf112.skeleton.app.Board;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.EnumSet;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardPiece extends Actor
{
    private Sprite sprite;
    private Vector<EnumSet<SquareType>> SquareTypes;

    private int width;
    private int height;

    public BoardPiece(Sprite sprite, int width, int height) {
        this.sprite = sprite;
        this.width = width;
        this.height = height;

        // Creates a list of length width*height containing empty bit flags
        SquareTypes = IntStream.range(0, width*height).mapToObj(n -> EnumSet.noneOf(SquareType.class)).collect(Collectors.toCollection(Vector::new));
        addSquareType(11, 0, SquareType.REPAIR);
        addSquareType(0, 11, SquareType.REPAIR);
        addSquareType(2, 2, SquareType.DOUBLE_REPAIR);
        addSquareType(5, 6, SquareType.DOUBLE_REPAIR);
        addSquareType(9, 7, SquareType.DOUBLE_REPAIR);

        addSquareType(7, 5, SquareType.HOLE);
        addSquareType(9, 8, SquareType.HOLE);
        addSquareType(5, 2, SquareType.HOLE);
        addSquareType(9, 2, SquareType.HOLE);
        addSquareType(1, 10, SquareType.HOLE);

        addInLine(6,8, Direction.NORTH, 4, SquareType.CONVEYOR_NORTH);
        addInLine(8,11, Direction.SOUTH, 3, SquareType.CONVEYOR_SOUTH);
        addInLine(11,10, Direction.WEST, 2, SquareType.CONVEYOR_WEST);
        addInLine(9,10, Direction.SOUTH, 2, SquareType.CONVEYOR_SOUTH);
        addInLine(11, 6, Direction.WEST, 3, SquareType.CONVEYOR_WEST);
        addSquareType(7,6, SquareType.CONVEYOR_WEST);
        addInLine(6, 6, Direction.SOUTH, 3, SquareType.CONVEYOR_SOUTH);
        addInLine(1, 6, Direction.WEST, 2, SquareType.CONVEYOR_WEST);
        addSquareType(0, 5, SquareType.CONVEYOR_EAST);
        addInLine(1, 5, Direction.SOUTH, 4, SquareType.CONVEYOR_SOUTH);
        addSquareType(1, 0, SquareType.CONVEYOR_SOUTH);
        addInLine(3, 0, Direction.NORTH, 3, SquareType.CONVEYOR_NORTH);
        addInLine(8, 2, Direction.SOUTH, 3, SquareType.CONVEYOR_SOUTH);
        addInLine(11, 3, Direction.WEST, 3, SquareType.CONVEYOR_WEST);
        addSquareType(5, 7, SquareType.CONVEYOR_EAST);

        addInLine(3, 4, Direction.NORTH, 8, SquareType.DOUBLE_CONVEYOR_NORTH);
        addInLine(5, 11, Direction.SOUTH, 2, SquareType.DOUBLE_CONVEYOR_SOUTH);
        addInLine(5, 9, Direction.WEST, 2, SquareType.DOUBLE_CONVEYOR_WEST);

        addSquareType(2, 11, SquareType.WALL_NORTH);
        addSquareType(4, 11, SquareType.WALL_NORTH);
        addSquareType(7, 11, SquareType.WALL_NORTH);
        addSquareType(9, 11, SquareType.WALL_NORTH);
        addSquareType(2, 9, SquareType.WALL_NORTH);
        addSquareType(8, 7, SquareType.WALL_NORTH);

        addSquareType(2, 0, SquareType.WALL_SOUTH);
        addSquareType(4, 0, SquareType.WALL_SOUTH);
        addSquareType(7, 0, SquareType.WALL_SOUTH);
        addSquareType(9, 0, SquareType.WALL_SOUTH);
        addSquareType(2, 4, SquareType.WALL_SOUTH);
        addSquareType(8, 5, SquareType.WALL_SOUTH);
        addSquareType(10, 5, SquareType.WALL_SOUTH);
        addSquareType(5, 8, SquareType.WALL_SOUTH);

        addSquareType(2, 10, SquareType.WALL_EAST);
        addSquareType(5, 10, SquareType.WALL_EAST);
        addSquareType(11, 9, SquareType.WALL_EAST);
        addSquareType(11, 7, SquareType.WALL_EAST);
        addSquareType(11, 4, SquareType.WALL_EAST);
        addSquareType(11, 2, SquareType.WALL_EAST);
        addSquareType(1, 1, SquareType.WALL_EAST);
        addSquareType(6, 3, SquareType.WALL_EAST);
        addSquareType(2, 3, SquareType.WALL_EAST);

        addSquareType(0, 9, SquareType.WALL_WEST);
        addSquareType(5, 8, SquareType.WALL_WEST);
        addSquareType(0, 7, SquareType.WALL_WEST);
        addSquareType(6, 6, SquareType.WALL_WEST);
        addSquareType(0, 4, SquareType.WALL_WEST);
        addSquareType(0, 2, SquareType.WALL_WEST);
        addSquareType(1, 1, SquareType.WALL_WEST);
        addSquareType(7, 1, SquareType.WALL_WEST);
        addSquareType(10, 1, SquareType.WALL_WEST);

        addSquareType(8, 3, SquareType.ROTATE_CCW);
        addSquareType(5, 3, SquareType.ROTATE_CCW);
        addSquareType(6, 7, SquareType.ROTATE_CCW);
        addSquareType(2, 5, SquareType.ROTATE_CCW);

        addSquareType(4,3, SquareType.ROTATE_CW);
        addSquareType(2,6, SquareType.ROTATE_CW);
        addSquareType(8,6, SquareType.ROTATE_CW);
        /*
        addInLine(5, 10, Direction.WEST, 3, SquareType.LASER);
        addInLine(2, 4, Direction.NORTH, 6, SquareType.LASER);
        addInLine(7, 1, Direction.EAST, 3, SquareType.LASER);
        addInLine(8, 5, Direction.NORTH, 3, SquareType.LASER);
        addInLine(6, 3, Direction.WEST, 4, SquareType.DOUBLE_LASER);
        addSquareType(1, 1, SquareType.TRIPLE_LASER);
        */

        //Todo: Laser system.
    }

    private void addSquareType(int x, int y, SquareType type)
    {
        this.SquareTypes.get(y*width+x).add(type);
    }

    private void addInLine(int x, int y, Direction direction, int length, SquareType type)
    {
        length -= 1;
        if(length <= 0){ throw new IllegalArgumentException(); } // Todo: Add more checks
        switch(direction)
        {
            case NORTH:
                IntStream.rangeClosed(y, y+length).forEach(n -> addSquareType(x, n, type));
                break;
            case SOUTH:
                IntStream.rangeClosed(y-length, y).forEach(n -> addSquareType(x, n, type));
                break;
            case WEST:
                IntStream.rangeClosed(x-length, x).forEach(n -> addSquareType(n, y, type));
                break;
            case EAST:
                IntStream.rangeClosed(x, x+length).forEach(n -> addSquareType(n, y, type));
                break;
        }
    }

    public EnumSet<SquareType> getSquareTypes(int x, int y)
    {
        return this.SquareTypes.get(y*width+x);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, 0, 0, 800, 800);
    }
}
