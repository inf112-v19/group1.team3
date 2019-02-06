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
import inf112.skeleton.app.Player.Piece;

public class RoboRally implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    private TextureRegion board;
    private Piece piece;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        this.board = new TextureRegion(new Texture(Gdx.files.internal("textures/board1.png")), 0, 0, 1800, 1800);
        this.piece = new Piece(new Sprite(new Texture(Gdx.files.internal("textures/piece1.png")), 32, 32));

        // Sets position on the 12x12 grid
        piece.setPosition(11, 11);
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
        piece.draw(batch, 1);
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
