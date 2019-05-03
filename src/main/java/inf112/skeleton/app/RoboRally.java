package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.Boards.ChopShop;
import inf112.skeleton.app.Card.Card;
import inf112.skeleton.app.Deck.Deck;
import inf112.skeleton.app.UI.UI;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class RoboRally implements ApplicationListener, InputProcessor {
    private SpriteBatch batch;
    private Game game;
    private UI ui;
    private ArrayList<Card> cards;
    private ArrayList<Card> chosen_cards;
    private boolean doDraw = true;

    private String addr;
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
            Socket socket = new Socket(addr, 2243);
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
        game = new Game(new ChopShop(), numPlayers);

        //Necessary for handling input
        Gdx.input.setInputProcessor(this);

        ui = new UI();

        //for testing
        Deck deck = new Deck();
        cards = deck.selectNine();
        chosen_cards = new ArrayList<>();

        /*
        for (int x = 0; x < 5; x++) {
            card(x);
        } // Chose 5 cards automatically for debugging
        */
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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Read input and send commands below this

        readKey(Input.Keys.UP);
        readKey(Input.Keys.DOWN);
        readKey(Input.Keys.LEFT);
        readKey(Input.Keys.RIGHT);
        readKey(Input.Keys.SPACE);
        // For testing game.move() in game.handleCommand()
        readKey(Input.Keys.ENTER);
        readKey(Input.Keys.R);

        if (chosen_cards.size() == 5) {
            doDraw = false;
            System.out.println("FIVE CARDS CHOSEN");
            StringBuilder cards = new StringBuilder();
            for (Card card : chosen_cards) {
                cards.append(card);
                cards.append(",");
            }
            sendCommand("CARDS", cards.toString());
            chosen_cards = new ArrayList<>();
        }

        // Read input and send commands above this

        try {
            server_writer.flush();
        } catch (IOException e) {
            System.err.println("Failed to send events to server: " + e);
            throw new RuntimeException();
        }

        String state = stateQueue.poll();
        if (state != null) {
            if (state.equals("State=Over")) {
                System.out.println("Game ended!");
                Gdx.app.exit();
                return;
            } else if (state.startsWith("State=")) game.setState(state);
            else {
                if (state.startsWith("WIN=")) {
                    System.out.println("Player " + state.split("=")[1] + " wins!");
                    Gdx.app.exit();
                    return;
                } else if (state.equals("StepRound")) {
                    doDraw = true;
                    chosen_cards = new ArrayList<>();
                }
            }
        }

        batch.begin();
        game.draw(batch);

        if (doDraw) ui.drawNine(batch, 1, cards);

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

    @Override
    public boolean keyDown(int keycode) {
        if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)){
            //
        }
        if(keycode == Input.Keys.LEFT) {
            //
        }
        if(keycode == Input.Keys.RIGHT) {
            //
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    private void card(int i) {
        if (chosen_cards.contains(cards.get(i))) {
            System.out.println("Player tried chose card, but it was already chosen.");
        } else {
            System.out.println("Player chose card: " + cards.get(i));
            chosen_cards.add(cards.get(i));
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT){
            //Card 1
            if(box1(screenX, screenY)) {
                card(0);
            }
            //Card 2
            if(box2(screenX, screenY)) {
                card(1);
            }
            //Card 3
            if(box3(screenX, screenY)) {
                card(2);
            }
            //Card 4
            if(box4(screenX, screenY)) {
                card(3);
            }
            //Card 5
            if(box5(screenX, screenY)) {
                card(4);
            }
            //Card 6
            if(box6(screenX, screenY)) {
                card(5);
            }
            //Card 7
            if(box7(screenX, screenY)) {
                card(6);
            }
            //Card 8
            if(box8(screenX, screenY)) {
                card(7);
            }
            //Card 9
            if(box9(screenX, screenY)) {
                card(8);
            }
        }
        if(button == Input.Buttons.RIGHT){
            //
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    //make UI area for Cards into boxes, to be used for UI input
    public boolean box1(int screenX, int screenY) {
        return screenX < 868 && screenX > 768 && screenY > 0 && screenY < 100;
    }

    public boolean box2(int screenX, int screenY) {
        return screenX < 968 && screenX > 868 && screenY > 0 && screenY < 100;
    }

    public boolean box3(int screenX, int screenY) {
        return screenX < 1068 && screenX > 968 && screenY > 0 && screenY < 100;
    }

    public boolean box4(int screenX, int screenY) {
        return screenX < 868 && screenX > 768 && screenY > 100 && screenY < 200;
    }

    public boolean box5(int screenX, int screenY) {
        return screenX < 968 && screenX > 868 && screenY > 100 && screenY < 200;
    }

    public boolean box6(int screenX, int screenY) {
        return screenX < 1068 && screenX > 968 && screenY > 100 && screenY < 200;
    }

    public boolean box7(int screenX, int screenY) {
        return screenX < 868 && screenX > 768 && screenY > 200 && screenY < 300;
    }

    public boolean box8(int screenX, int screenY) {
        return screenX < 968 && screenX > 868 && screenY > 200 && screenY < 300;
    }

    public boolean box9(int screenX, int screenY) {
        return screenX < 1068 && screenX > 968 && screenY > 200 && screenY < 300;
    }

}
