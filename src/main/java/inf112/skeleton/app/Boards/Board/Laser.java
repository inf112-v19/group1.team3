package inf112.skeleton.app.Boards.Board;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Direction;

class Laser {
    public Direction direction;
    public int strength;
    public Vector2 position;

    Laser(Direction direction, int strength, Vector2 position) {
        this.direction = direction;
        this.strength = strength;
        this.position = position;
    }
}
