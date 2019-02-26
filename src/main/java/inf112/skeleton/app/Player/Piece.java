package inf112.skeleton.app.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Piece extends Actor {
    private Sprite sprite;
    private Vector2 position = new Vector2(0,0);

    public Piece(Sprite sprite) {
        this.sprite = sprite;
        this.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    protected void positionChanged() {
        Vector2 new_position = new Vector2(getX(), getY()); // Store it in a new variable in case the move is illegal
        if(new_position.x > 11 || new_position.x < 0 || new_position.y > 11 || new_position.y < 0) throw new IllegalArgumentException();
        this.sprite.setPosition(Gdx.graphics.getWidth()/12f*(0.25f+new_position.x), Gdx.graphics.getWidth()/12f*(0.25f+new_position.y));
        this.position = new_position; // Movement complete, update stored position
    }

    public Vector2 getPosition() {
        return position;
    }
}
