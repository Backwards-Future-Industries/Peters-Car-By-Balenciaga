import gameEngine.GameEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import utilities.GameData;
import utilities.SPIlocator;

import java.awt.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    
    @Test
    public void OpenWindowTest(){
        Assertions.assertEquals("Peter's car",gameEngine.getWindow().getTitle());
        Assertions.assertEquals(dimension.width,gameEngine.getWindow().getWidth());
        Assertions.assertEquals(1,gameEngine.getWindow().getKeyListeners().length);
        Assertions.assertTrue(gameEngine.getWindow().isVisible());
    }



}
