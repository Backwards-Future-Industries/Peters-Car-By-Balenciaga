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
        int lenght = 500;
        gameEngine.window.setSize(lenght,lenght);
        Assertions.assertEquals("Peter's car",gameEngine.window.getTitle());
        Assertions.assertEquals(lenght,gameEngine.window.getWidth());
        Assertions.assertEquals(lenght,gameEngine.window.getHeight());
        Assertions.assertEquals(1,gameEngine.window.getKeyListeners().length);
        Assertions.assertTrue(gameEngine.window.isVisible());
    }

}
