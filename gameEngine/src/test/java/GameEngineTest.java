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
        Assertions.assertEquals("Peter's car",gameEngine.window.getTitle());
        Assertions.assertEquals(1000,gameEngine.window.getWidth());
        Assertions.assertEquals(1000,gameEngine.window.getHeight());
        Assertions.assertEquals(1,gameEngine.window.getKeyListeners().length);
        Assertions.assertTrue(gameEngine.window.isVisible());
    }

}