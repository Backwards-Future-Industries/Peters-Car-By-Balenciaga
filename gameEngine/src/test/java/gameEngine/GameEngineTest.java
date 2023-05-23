package gameEngine;

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
import static org.junit.jupiter.api.condition.OS.WINDOWS;

class GameEngineTest {

    //Arrange
    private GameEngine gameEngine;
    private Dimension dimension;
    @Mock
    private GameData gameData;

    @BeforeEach
    void setUp() {
        //Arrange
        MockitoAnnotations.initMocks(this);
        this.dimension = new Dimension(500,700);
        this.gameEngine = new GameEngine(60, gameData, dimension);
    }

    @EnabledOnOs(LINUX)
    @Test
    public void OpenWindowTestOnLinux_fr4(){
        //Assert
        Assertions.assertEquals("Peter's car",gameEngine.getWindow().getTitle());
        Assertions.assertEquals(dimension.width ,gameEngine.getWindow().getWidth());
        Assertions.assertEquals(1,gameEngine.getWindow().getKeyListeners().length);
        Assertions.assertTrue(gameEngine.getWindow().isVisible());
    }

    @EnabledOnOs(WINDOWS)
    @Test
    public void OpenWindowTestOnWindows_fr4(){
        //Assert
        Assertions.assertEquals("Peter's car",gameEngine.getWindow().getTitle());
        Assertions.assertEquals(dimension.width ,gameEngine.getWindow().getWidth()-16);
        Assertions.assertEquals(1,gameEngine.getWindow().getKeyListeners().length);
        Assertions.assertTrue(gameEngine.getWindow().isVisible());
    }
}
