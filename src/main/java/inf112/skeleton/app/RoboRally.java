package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Boards.Board.Board;
import inf112.skeleton.app.Boards.ChopShop;

public class RoboRally implements ApplicationListener {
    private SpriteBatch batch;
    private Board board;
    private inf112.skeleton.app.Player.Piece piece;

    @Override
    public void create() {
        batch = new SpriteBatch();

        //this.board = new TextureRegion(new Texture(Gdx.files.internal("textures/board1.png")), 0, 0, 1800, 1800);
        this.board = new ChopShop();
        this.piece = new inf112.skeleton.app.Player.Piece("textures/piece1/$dir.png");
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
            if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) piece.setPosition(piece.getBackward());
            if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) piece.setPosition(piece.getForward());
            if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) piece.rotateCCW();
            if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) piece.rotateCW();
        }
        catch(IllegalArgumentException e)
        {
            System.err.println("Tried to move out of bounds");
        }

        // Has moved, check board
        if(piece.getPosition() != position)
        {
            System.out.println("Moved from " + position + " to " + piece.getPosition());
            System.out.println(board.getSquareTypes((int) piece.getPosition().x, (int) piece.getPosition().y));
            System.out.println("Hit laser: " + board.getLasers((int) piece.getPosition().x, (int) piece.getPosition().y));
        }

        batch.begin();
        //batch.draw(board, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        board.draw(batch, 1);
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
