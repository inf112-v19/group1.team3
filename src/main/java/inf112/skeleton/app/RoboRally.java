package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Player.Piece;

public class RoboRally implements ApplicationListener {
    private SpriteBatch batch;
    private TextureRegion board;
    private Piece piece;

    @Override
    public void create() {
        batch = new SpriteBatch();

        this.board = new TextureRegion(new Texture(Gdx.files.internal("textures/board1.png")), 0, 0, 1800, 1800);
        this.piece = new Piece(new Sprite(new Texture(Gdx.files.internal("textures/piece1.png")), 32, 32));

        // Sets position on the 12x12 grid
        piece.setPosition(11, 10);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Vector2 position = piece.getPosition();
        try
        {
            if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) piece.setPosition(position.x, position.y-1);
            if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) piece.setPosition(position.x, position.y+1);
            if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) piece.setPosition(position.x-1, position.y);
            if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) piece.setPosition(position.x+1, position.y);
        }
        catch(IllegalArgumentException e)
        {
            System.err.println("Tried to move out of bounds");
        }

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
