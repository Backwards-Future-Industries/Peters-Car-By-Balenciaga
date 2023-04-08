import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testOpenWindow(){
        assertEquals("Peter's car",gameEngine.window.getTitle());
        assertEquals(1000,gameEngine.window.getWidth());
        assertEquals(1000,gameEngine.window.getHeight());
        assertEquals(1,gameEngine.window.getKeyListeners().length);
        assertTrue(gameEngine.window.isVisible());
    }

}