package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.Boards.ChopShop;

import java.io.*;
import java.net.Socket;

public class RoboRally implements ApplicationListener {
    private SpriteBatch batch;
    private Game game;

    private Socket socket;
    private BufferedWriter server_writer;
    private BufferedReader server_reader;

    @Override
    public void create()
    {
        batch = new SpriteBatch();
        game = new Game(new ChopShop(), 4); // TODO: Replace with fake board taking state from server
        try
        {
            socket = new Socket("localhost", 2243);
            server_writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            server_reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e)
        {
            System.err.println("Failed to connect to server: " + e);
            throw new RuntimeException();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private void readKey(int key)
    {
        if (Gdx.input.isKeyJustPressed(key))
        {
            try
            {
                server_writer.write("KEY=" + key + "\n");
            }
            catch(IOException e)
            {
                System.err.println("Failed to send event to server: " + e);
                throw new RuntimeException();
            }
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        readKey(Input.Keys.UP);
        readKey(Input.Keys.DOWN);
        readKey(Input.Keys.LEFT);
        readKey(Input.Keys.RIGHT);
        try { server_writer.flush(); }
        catch (IOException e) { System.err.println("Failed to send events to server: " + e); throw new RuntimeException(); }

        //fakeGame.setState(server_reader.getState());
        //game.setState ?

        batch.begin();
        game.draw(batch);
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
