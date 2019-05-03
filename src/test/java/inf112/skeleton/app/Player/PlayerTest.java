package inf112.skeleton.app.Player;

import com.badlogic.gdx.math.Vector2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerTest {

    Vector2 position = new Vector2(0, 0);
    Player player = new Player(1, position);

    // Check if player ends up at starting pos after respawn
    @Test
    public void reSpawn() {
        player.piece.setPosition(new Vector2(10,10));
        player.respawn();
        Vector2 positionStart = new Vector2(0,0);
        assertEquals(player.piece.getPosition(), positionStart);
    }
}

