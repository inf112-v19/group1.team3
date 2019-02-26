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

        addInLine(3, 4, Direction.NORTH, 8, SquareType.DOUBLE_CONVEYOR_NORTH);
        addInLine(5, 11, Direction.SOUTH, 2, SquareType.DOUBLE_CONVEYOR_SOUTH);
        addInLine(5, 9, Direction.WEST, 2, SquareType.DOUBLE_CONVEYOR_WEST);

        addInLine(5, 10, Direction.WEST, 3, SquareType.LASER);
        addInLine(2, 4, Direction.NORTH, 6, SquareType.LASER);
        addInLine(7, 1, Direction.EAST, 3, SquareType.LASER);
        addInLine(8, 5, Direction.NORTH, 3, SquareType.LASER);
        addInLine(6, 3, Direction.WEST, 4, SquareType.DOUBLE_LASER);
        addSquareType(1, 1, SquareType.TRIPLE_LASER);

        //Todo: The rest of the things
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
