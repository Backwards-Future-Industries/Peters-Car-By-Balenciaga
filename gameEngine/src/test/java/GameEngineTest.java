import gameEngine.GameEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utilities.GameData;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameEngineTest {

    private GameEngine gameEngine;

    @Mock
    GameData gameData;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        //when(gameData.getMap()).then(new MockMap());
        this.gameEngine = new GameEngine(60, gameData);
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
