package inf112.skeleton.app.Boards.Board;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import inf112.skeleton.app.Direction;

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
        lasers.add(new Laser(direction, strength, new Vector2(x, y)));
    }

    public int getLasers(Vector2 pos)
    {
        return getLasers((int) pos.x, (int) pos.y);
    }

    public int getLasers(int x, int y)
    {
        int laserPower = 0;
        for(Laser laser : lasers)
        {
            if(traceLaser(laser).getPositions().contains(new Vector2((float) x, (float) y)))
            {
                laserPower += laser.strength;
            }
        }

        return laserPower;
    }

    private TraceResult traceLaser(Laser laser)
    {
        return traceLine(laser.position, laser.direction);
    }

    public TraceResult traceLine(Vector2 startPos, Direction direction)
    {
        Vector<Vector2> positions = new Vector<>();
        switch(direction)
        {
            case NORTH:
                for(float y = startPos.y; y < this.height; y++)
                {
                    EnumSet<SquareType> types = getSquareTypes((int) startPos.x, (int) y);

                    if(types.contains(SquareType.WALL_SOUTH) && y != startPos.y)
                    {
                        // Don't enter this square
                        return new TraceResult(positions);
                    }
                    else if(types.contains(SquareType.WALL_NORTH))
                    {
                        // Stop after entering this square
                        positions.add(new Vector2(startPos.x, y));
                        return new TraceResult(positions);
                    }
                    // Todo: Check for players
                    else
                    {
                        positions.add(new Vector2(startPos.x, y));
                    }
                }
                return new TraceResult(positions, false); // Hit no walls, exited map
            case SOUTH:
                for(float y = startPos.y; y >= 0; y--)
                {
                    EnumSet<SquareType> types = getSquareTypes((int) startPos.x, (int) y);

                    if(types.contains(SquareType.WALL_NORTH) && y != startPos.y)
                    {
                        // Don't enter this square
                        return new TraceResult(positions);
                    }
                    else if(types.contains(SquareType.WALL_SOUTH))
                    {
                        // Stop after entering this square
                        positions.add(new Vector2(startPos.x, y));
                        return new TraceResult(positions);
                    }
                    // Todo: Check for players
                    else
                    {
                        positions.add(new Vector2(startPos.x, y));
                    }
                }
                return new TraceResult(positions, false); // Hit no walls, exited map
            case WEST:
                for(float x = startPos.x; x >= 0; x--)
                {
                    EnumSet<SquareType> types = getSquareTypes((int) x, (int) startPos.y);

                    if(types.contains(SquareType.WALL_EAST) && x != startPos.x)
                    {
                        // Don't enter this square
                        return new TraceResult(positions);
                    }
                    else if(types.contains(SquareType.WALL_WEST))
                    {
                        // Stop after entering this square
                        positions.add(new Vector2(x, startPos.y));
                        return new TraceResult(positions);
                    }
                    // Todo: Check for players
                    else
                    {
                        positions.add(new Vector2(x, startPos.y));
                    }
                }
                return new TraceResult(positions, false); // Hit no walls, exited map
            case EAST:
                for(float x = startPos.x; x < this.width; x++)
                {
                    EnumSet<SquareType> types = getSquareTypes((int) x, (int) startPos.y);

                    if(types.contains(SquareType.WALL_WEST) && x != startPos.x)
                    {
                        // Don't enter this square
                        return new TraceResult(positions);
                    }
                    else if(types.contains(SquareType.WALL_EAST))
                    {
                        // Stop after entering this square
                        positions.add(new Vector2(x, startPos.y));
                        return new TraceResult(positions);
                    }
                    // Todo: Check for players
                    else
                    {
                        positions.add(new Vector2(x, startPos.y));
                    }
                }
                return new TraceResult(positions, false); // Hit no walls, exited map
        }

        return new TraceResult(positions, false);
    }

    public boolean isInBounds(Vector2 pos)
    {
        return isInBounds((int) pos.x, (int) pos.y);
    }

    public boolean isInBounds(int x, int y)
    {
        return !(x < 0 || x > width || y < 0 || y > height);
    }

    protected void addSquareType(int x, int y, SquareType type)
    {
        if (!isInBounds(x, y)) { throw new IndexOutOfBoundsException(); }
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

    public EnumSet<SquareType> getSquareTypes(Vector2 pos){ return getSquareTypes((int) pos.x, (int) pos.y); }

    public EnumSet<SquareType> getSquareTypes(int x, int y)
    {
        return this.squareTypes.get(y*width+x);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, 0, 0, this.width*64, this.height*64);
    }
}
