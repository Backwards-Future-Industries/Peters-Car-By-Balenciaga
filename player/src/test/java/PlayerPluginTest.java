import abstractClasses.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Player;

public class PlayerPluginTest {
    private int[] position;
    private Entity playerPlugin;

    @BeforeEach
    void setPlayerPlugin() {
        this.playerPlugin = new Player();
        this.playerPlugin.setPosition(new int[]{700, 500});
        this.position = new int[]{700, 500};
    }

    @Test
    public void testPosition() {
        Assertions.assertEquals(this.position[0], playerPlugin.getPosition()[0]);
        Assertions.assertEquals(this.position[1], playerPlugin.getPosition()[1]);
    }
}



