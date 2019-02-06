package inf112.skeleton.app.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Piece extends Actor {
    private Sprite sprite;

    public Piece(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    protected void positionChanged() {
        float x = getX();
        float y = getY();
        if(x > 11 || x < 0 || y > 11 || y < 0) throw new IllegalArgumentException();
        this.sprite.translate(Gdx.graphics.getWidth()/12f*(0.25f+x), Gdx.graphics.getWidth()/12f*(0.25f+y));
    }
}
