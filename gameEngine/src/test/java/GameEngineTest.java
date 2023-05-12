import gameEngine.GameEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.io.IOException;

class GameEngineTest {

    private GameEngine gameEngine;

    @BeforeEach
    void setUp() throws IOException {
        this.gameEngine = new GameEngine(60);
    }

    @Test
    public void OpenWindowTest(){
        int length = 500;
        gameEngine.getWindow().setSize(length,length);
        Assertions.assertEquals("Peter's car",gameEngine.getWindow().getTitle());
        Assertions.assertEquals(length,gameEngine.getWindow().getWidth());
        Assertions.assertEquals(length,gameEngine.getWindow().getHeight());
        Assertions.assertEquals(1,gameEngine.getWindow().getKeyListeners().length);
        Assertions.assertTrue(gameEngine.getWindow().isVisible());
    }

}
