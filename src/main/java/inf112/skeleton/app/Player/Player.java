package inf112.skeleton.app.Player;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Card.Card;
import inf112.skeleton.app.Direction;

import java.util.ArrayList;

public class Player {

    private Vector2 position = new Vector2(0, 0);

    private int hp = 3;
    public Piece piece;
    public ArrayList<Card> program;

    private int id;
    private Vector2 spawn;

    // Default constructor
    public Player(int id, Vector2 spawn) {
        piece = new Piece();
        this.id = id;
        this.spawn = spawn;

        this.respawn();
    }

    public Player(String spritePath, int id, Vector2 spawn) {
        piece = new Piece(spritePath);
        this.id = id;
        this.spawn = spawn;

        this.respawn();
    }

    // Sets hp to max
    public void repair() {
        hp = 3;
    }

    // Does n damage and returns the remaining hp
    public int hit(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
        return hp;
    }

    public void respawn() {
        piece.setPosition(spawn);
        piece.setDirection(Direction.NORTH);
        print("was respawned.");
    }

    public void print(String message) {
        System.out.println("Player " + (this.id + 1) + " " + message);
    }

    public int getHp() {
        return hp;
    }

    public void setProgram(ArrayList<Card> program) {
        this.program = program;
    }

    // "Consumes" the first card of the program
    public Card takeCard() {
        return program.remove(0);
    }
}
