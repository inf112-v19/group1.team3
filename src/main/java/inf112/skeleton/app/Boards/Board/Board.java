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
            if(traceLaser(laser).contains(new Vector2((float) x, (float) y)))
            {
                laserPower += laser.strength;
            }
        }

        return laserPower;
    }

    private Vector<Vector2> traceLaser(Laser laser)
    {
        Vector2 startPos = laser.position;
        Vector<Vector2> positions = new Vector<>();
        switch(laser.direction)
        {
            case NORTH:
                for(float y = startPos.y; y < this.height; y++)
                {
                    EnumSet<SquareType> types = getSquareTypes((int) startPos.x, (int) y);

                    if(types.contains(SquareType.WALL_SOUTH) && y != startPos.y)
                    {
                        // Don't enter this square
                        return positions;
                    }
                    else if(types.contains(SquareType.WALL_NORTH))
                    {
                        // Stop after entering this square
                        positions.add(new Vector2(startPos.x, y));
                        return positions;
                    }
                    // Todo: Check for players
                    else
                    {
                        positions.add(new Vector2(startPos.x, y));
                    }
                }
                return positions; // Hit no walls, exited map
            case SOUTH: // TODO: Check if this actually works, currently no south-facing lasers
                for(float y = startPos.y; y > 0; y--)
                {
                    EnumSet<SquareType> types = getSquareTypes((int) startPos.x, (int) y);

                    if(types.contains(SquareType.WALL_NORTH) && y != startPos.y)
                    {
                        // Don't enter this square
                        return positions;
                    }
                    else if(types.contains(SquareType.WALL_SOUTH))
                    {
                        // Stop after entering this square
                        positions.add(new Vector2(startPos.x, y));
                        return positions;
                    }
                    // Todo: Check for players
                    else
                    {
                        positions.add(new Vector2(startPos.x, y));
                    }
                }
                return positions; // Hit no walls, exited map
            case WEST:
                for(float x = startPos.x; x > 0; x--)
                {
                    EnumSet<SquareType> types = getSquareTypes((int) x, (int) startPos.y);

                    if(types.contains(SquareType.WALL_EAST) && x != startPos.x)
                    {
                        // Don't enter this square
                        return positions;
                    }
                    else if(types.contains(SquareType.WALL_WEST))
                    {
                        // Stop after entering this square
                        positions.add(new Vector2(x, startPos.y));
                        return positions;
                    }
                    // Todo: Check for players
                    else
                    {
                        positions.add(new Vector2(x, startPos.y));
                    }
                }
                return positions; // Hit no walls, exited map
            case EAST:
                for(float x = startPos.x; x < this.width; x++)
                {
                    EnumSet<SquareType> types = getSquareTypes((int) x, (int) startPos.y);

                    if(types.contains(SquareType.WALL_WEST) && x != startPos.x)
                    {
                        // Don't enter this square
                        return positions;
                    }
                    else if(types.contains(SquareType.WALL_EAST))
                    {
                        // Stop after entering this square
                        positions.add(new Vector2(x, startPos.y));
                        return positions;
                    }
                    // Todo: Check for players
                    else
                    {
                        positions.add(new Vector2(x, startPos.y));
                    }
                }
                return positions; // Hit no walls, exited map
        }

        return positions;
    }

    protected void addSquareType(int x, int y, SquareType type)
    {
        if (x < 0 || x > width || y < 0 || y > height) { throw new IndexOutOfBoundsException(); }
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
        batch.draw(sprite, 0, 0, this.width*64, this.height*64);
    }
}
