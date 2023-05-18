import gameEngine.GameEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utilities.GameData;

import java.awt.*;

import static org.junit.jupiter.api.condition.OS.LINUX;

class GameEngineTest {

    private GameEngine gameEngine;
    private Dimension dimension;

    @Mock
    private GameData gameData;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.dimension = new Dimension(500,700);
        this.gameEngine = new GameEngine(60, gameData, dimension);
    }

    @EnabledOnOs(LINUX)
    @Test
    public void OpenWindowTest(){
        Assertions.assertEquals("Peter's car",gameEngine.getWindow().getTitle());
        Assertions.assertEquals(dimension.width,gameEngine.getWindow().getWidth());
        Assertions.assertEquals(1,gameEngine.getWindow().getKeyListeners().length);
        Assertions.assertTrue(gameEngine.getWindow().isVisible());
    }



}
