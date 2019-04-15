package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.Boards.ChopShop;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class RoboRally implements ApplicationListener {
    private SpriteBatch batch;
    private Game game;

    private String addr;
    private Socket socket;
    private BufferedWriter server_writer;
    private BufferedReader server_reader;

    private LinkedBlockingQueue<String> stateQueue;

    public RoboRally() {
        this.addr = "localhost";
    }

    public RoboRally(String addr) {
        this.addr = addr;
    }

    private String receiveCommand() {
        try {
            return server_reader.readLine();
        } catch (IOException e) {
            System.err.println("Failed to receive command from server: " + e);
            throw new RuntimeException();
        }
    }

    @Override
    public void create() {
        try {
            socket = new Socket(addr, 2243);
            server_writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            server_reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Failed to connect to server: " + e);
            throw new RuntimeException();
        }

        int numPlayers = Integer.parseInt(receiveCommand().split("=")[1]);

        stateQueue = new LinkedBlockingQueue<>();
        new StateListener(server_reader, stateQueue).start();

        batch = new SpriteBatch();
        game = new Game(new ChopShop(), numPlayers); // TODO: Replace with fake board taking state from server
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private void readKey(int key) {
        if (Gdx.input.isKeyJustPressed(key)) {
            sendCommand("KEY", String.valueOf(key));
        }
    }

    private void sendCommand(String command, String value) {
        try {
            server_writer.write(command + "=" + value + "\n");
        } catch (IOException e) {
            System.err.println("Failed to send command to server: " + e);
            throw new RuntimeException();
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Read input and send commands below this

        readKey(Input.Keys.UP);
        readKey(Input.Keys.DOWN);
        readKey(Input.Keys.LEFT);
        readKey(Input.Keys.RIGHT);
        readKey(Input.Keys.SPACE);

        // Read input and send commands above this

        try {
            server_writer.flush();
        } catch (IOException e) {
            System.err.println("Failed to send events to server: " + e);
            throw new RuntimeException();
        }

        String state = stateQueue.poll();
        if (state != null) {
            game.setState(state);
        }

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
