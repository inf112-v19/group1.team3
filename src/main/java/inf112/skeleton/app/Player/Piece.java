package inf112.skeleton.app.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import inf112.skeleton.app.Direction;

public class Piece extends Actor {
    private Sprite[] sprites = new Sprite[4];
    private Vector2 position = new Vector2(0,0);
    private Direction direction = Direction.NORTH;

    public Piece(String spritePath) {
        this.sprites[0] = new Sprite(new Texture(Gdx.files.internal(spritePath.replace("$dir", "north"))));
        this.sprites[1] = new Sprite(new Texture(Gdx.files.internal(spritePath.replace("$dir", "east"))));
        this.sprites[2] = new Sprite(new Texture(Gdx.files.internal(spritePath.replace("$dir", "south"))));
        this.sprites[3] = new Sprite(new Texture(Gdx.files.internal(spritePath.replace("$dir", "west"))));
        this.positionChanged();
    }

    public void rotateCW()
    {
        direction = Direction.rotateCW(direction);
    }

    public void rotateCCW()
    {
        direction = Direction.rotateCCW(direction);
    }


    public Vector2 getForward()
    {
        return new Vector2(position).add(Direction.toVector2(direction));
    }

    public Vector2 getBackward()
    {
        return new Vector2(position).sub(Direction.toVector2(direction));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprites[Direction.toInt(direction)].draw(batch);
    }

    public void setPosition(Vector2 new_position)
    {
        setPosition(new_position.x, new_position.y);
    }

    @Override
    protected void positionChanged()
    {
        Vector2 new_position = new Vector2(getX(), getY()); // Store it in a new variable in case the move is illegal
        if(new_position.x > 11 || new_position.x < 0 || new_position.y > 11 || new_position.y < 0) throw new IllegalArgumentException();
        for(Sprite sprite : sprites)
        {
            sprite.setPosition(64*new_position.x+16, 64*new_position.y+16);
        }
        this.position = new_position; // Movement complete, update stored position
    }

    public Vector2 getPosition() {
        return position;
    }
    public Direction getDirection() { return direction; }
}
