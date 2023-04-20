import abstractClasses.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerPlugin;

import java.io.IOException;

public class PlayerPluginTest {
    private double position;
    private Entity playerPlugin;

    @BeforeEach
    void setPlayerPlugin(){
        try {
            this.playerPlugin = new PlayerPlugin().create();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.position = 10.0;

    }

    @Test
    public void testPosition(){
        Assertions.assertEquals(position,playerPlugin.getPosition()[0]);
        Assertions.assertEquals(position,playerPlugin.getPosition()[1]);
    }
}



