package inf112.skeleton.app.Player;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Direction;

import java.util.ArrayList;

public class Player {
    public int hp = 10;
    public int life = -1;
    public Piece piece;
    public ArrayList<String> program;

    public int id;
    public Vector2 spawn;
    public String lastCard;

    public boolean hasFlag1;

    // Default constructor
    public Player(int id, Vector2 spawn) {
        piece = new Piece();
        this.id = id;
        this.spawn = spawn;
        program = new ArrayList<>();
        lastCard = "";

        this.respawn();
    }

    // spawn is the "default location" to which the player gets reset on death. should be updated as the player reaches
    // new checkpoints
    public Player(String spritePath, int id, Vector2 spawn) {
        piece = new Piece(spritePath);
        this.id = id;
        this.spawn = spawn;

        this.respawn();
    }

    // Moves the player back to it's last known checkpoint, resets health
    public void respawn() {
        piece.setPosition(spawn);
        piece.setDirection(Direction.NORTH);
        hp = 10;
        life += 1;
        print("was respawned.");
    }

    // For debug use, outputs a string annotated with the player identification number
    public void print(String message) {
        //System.out.println("Player " + (this.id + 1) + " " + message); // Disabled for release
    }

    // "Consumes" the first card of the program
    public String takeCard() {

        String card = program.remove(0);

        lastCard = card;

        return card;
    }

    public String lastCard() {
        return lastCard;
    }
}
