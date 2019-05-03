package inf112.skeleton.app.Player;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Direction;

import java.util.ArrayList;

public class Player {

    private Vector2 position = new Vector2(0, 0);

    public int hp = 10;
    public int life = 3;
    public Piece piece;
    public ArrayList<String> program;

    public int id;
    public Vector2 spawn;
    private String lastCard;

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

    // Sets hp to max
    public void repair() {
        hp = 10;
    }

    // Does n damage and returns the remaining hp
    public int hit(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
        return hp;
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
        System.out.println("Player " + (this.id + 1) + " " + message);
    }

    public int getHp() {
        return hp;
    }

    public void decreaseHp() {
        hp--;
    }

    public void setProgram(ArrayList<String> program) {
        this.program = program;
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

    public void setCard(String card) {
        program.add(card);
    }
}
