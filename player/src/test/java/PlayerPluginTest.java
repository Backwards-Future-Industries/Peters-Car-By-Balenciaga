import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerPlugin;

import java.io.IOException;

public class PlayerPluginTest {
    private PlayerPlugin playerPlugin;


    @BeforeEach
    void setPlayerPlugin(){
        try {
            this.playerPlugin = new PlayerPlugin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testReturnType(){

        Assertions.assertEquals(10,playerPlugin.getPosition()[0]);
        Assertions.assertEquals(10,playerPlugin.getPosition()[1]);
    }
}



