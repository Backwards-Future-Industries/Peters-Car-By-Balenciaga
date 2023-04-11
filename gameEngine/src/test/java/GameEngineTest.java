import gameEngine.GameEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GameEngineTest {

    private GameEngine gameEngine;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.gameEngine = new GameEngine(60);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        gameEngine.stop();
    }

    @Tag("test")
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
