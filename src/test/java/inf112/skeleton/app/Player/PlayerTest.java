package inf112.skeleton.app.Player;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {


    Player player = new Player();

    @Test
    public void getPosition() {
        System.out.println(player.piece.getPosition());
    }
}