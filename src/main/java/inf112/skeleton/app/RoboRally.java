package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RoboRally implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    private TextureRegion board;
    private Sprite sprite;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        this.board = new TextureRegion(new Texture(Gdx.files.internal("textures/board1.png")), 0, 0, 1800, 1800);
        this.sprite = new Sprite(new Texture(Gdx.files.internal("textures/piece1.png")), 32, 32);

        // Sets position on the 12x12 grid
        setPos(11, 11);
    }

    private void setPos(int x, int y) {
        if(x > 11 || x < 0 || y > 11 || y < 0) throw new IllegalArgumentException();
        this.sprite.translate(Gdx.graphics.getWidth()/12f*(0.25f+x), Gdx.graphics.getWidth()/12f*(0.25f+y));
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(board, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
