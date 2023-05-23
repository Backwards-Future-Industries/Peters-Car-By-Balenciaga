package gameEngine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

class DrawLoopTest {
    //Arrange
    @Mock
    private GameEngine gameEngine;

    private DrawLoop drawLoop;

    @BeforeEach
    void setUp() {
        //Arrange
        MockitoAnnotations.initMocks(this);
        this.drawLoop =  new DrawLoop(gameEngine);
    }

    @Test
    void testRun() {
        //Act
        assertDoesNotThrow(() ->
                drawLoop.run()
        );
    }
}