package inf112.skeleton.app.Player;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Card.Card;

import java.util.ArrayList;

public class Player {

    private Vector2 position = new Vector2(0, 0);

    private int hp = 3;
    public Piece piece;
    public ArrayList<Card> program;

    // Default constructor
    public Player() {
        piece = new Piece();
    }

    public Player(String spritePath) {
        piece = new Piece(spritePath);
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
