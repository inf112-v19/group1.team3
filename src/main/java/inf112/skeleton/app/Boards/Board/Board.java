package inf112.skeleton.app.Boards.Board;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.EnumSet;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board extends Actor
{
    private Sprite sprite;
    private Vector<EnumSet<SquareType>> squareTypes;
    private Vector<Laser> lasers;

    private int width;
    private int height;

    public Board(Sprite sprite, int width, int height) {
        this.sprite = sprite;
        this.width = width;
        this.height = height;
        lasers = new Vector<>();

        // Creates a list of length width*height containing empty bit flags
        squareTypes = IntStream.range(0, width*height).mapToObj(n -> EnumSet.noneOf(SquareType.class)).collect(Collectors.toCollection(Vector::new));
    }

    protected void addLaser(int x, int y, Direction direction, int strength)
    {
        lasers.add(new Laser(direction, 1, new Vector2(x, y)));
    }

    protected void addSquareType(int x, int y, SquareType type)
    {
        this.squareTypes.get(y*width+x).add(type);
    }

    protected void addInLine(int x, int y, Direction direction, int length, SquareType type)
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
        return this.squareTypes.get(y*width+x);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, 0, 0, 800, 800);
    }
}
