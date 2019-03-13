package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;

public enum Direction
{
    NORTH, SOUTH, WEST, EAST;

    public static int toInt(Direction direction)
    {
        switch(direction)
        {
            case NORTH: return 0;
            case EAST: return 1;
            case SOUTH: return 2;
            default: return 3;
        }
    }

    public static Direction rotateCW(Direction direction)
    {
        switch(direction)
        {
            case NORTH: return EAST;
            case EAST: return SOUTH;
            case SOUTH: return WEST;
            default: return NORTH;
        }
    }

    public static Direction rotateCCW(Direction direction)
    {
        switch(direction)
        {
            case NORTH: return WEST;
            case EAST: return NORTH;
            case SOUTH: return EAST;
            default: return SOUTH;
        }
    }

    public static Vector2 toVector2(Direction direction)
    {
        switch(direction)
        {
            case NORTH: return new Vector2(0, 1);
            case EAST: return new Vector2(1, 0);
            case SOUTH: return new Vector2(0, -1);
            default: return new Vector2(-1, 0);
        }
    }
}
