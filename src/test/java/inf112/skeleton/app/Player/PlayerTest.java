package inf112.skeleton.app.Player;

import com.badlogic.gdx.math.Vector2;
import org.junit.Test;

public class PlayerTest {


    private Player player = new Player(1, new Vector2(0, 0));

    @Test
    public void getPosition() {
        System.out.println(player.piece.getPosition());
    }
}