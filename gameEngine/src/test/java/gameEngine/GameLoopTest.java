package gameEngine;

import interfaces.IProcessing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utilities.GameData;
import utilities.Inputs;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GameLoopTest {
    //Arrange
    @Mock
    private GameEngine gameEngine;
    private LinkedList<IProcessing> list;
    @Mock
    private GameData gameData;

    private GameLoop gameLoop;

    @BeforeEach
    void setUp() {
        //Arrange
        MockitoAnnotations.initMocks(this);
        this.gameLoop =  new GameLoop(new ArrayList<>(),gameEngine);
        this.list = new LinkedList<>();
        this.list.add(new IProcessing() {
            @Override
            public void process(ArrayList<Inputs> inputs, GameData gameData) {
                System.out.println("Hej");
            }
        });
    }

    @Test
    void testRun_fr4_2() {
        //Act
        when(gameEngine.getGameData()).thenReturn(gameData);
        when(gameEngine.getGameData().getProcesses()).thenReturn(this.list);
        assertDoesNotThrow(() ->
                gameLoop.run()
        );
    }
}