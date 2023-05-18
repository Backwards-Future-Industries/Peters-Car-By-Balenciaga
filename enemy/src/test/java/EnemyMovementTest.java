import abstractClasses.Entity;
import enemy.EnemyMovement;
import enemy.aiMovement.AIMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utilities.GameData;
import utilities.Inputs;
import utilities.SPIlocator;
import utilities.Type;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EnemyMovementTest {

    @Mock
    private AIMovement aiMovementMock;

    @Mock
    private Entity enemyMock;

    @Mock
    private GameData gameDataMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcess() {
        // Arrange
        ArrayList<Inputs> inputs = new ArrayList<>();
        when(gameDataMock.getEntityList(Type.ENEMY)).thenReturn(new ArrayList<Entity>() {{
            add(enemyMock);
        }});

        when(enemyMock.getRadians()).thenReturn(0.0);
        when(enemyMock.getPosition()).thenReturn(new int[]{0, 0});
        when(enemyMock.getSprite()).thenReturn(new Sprite());

        when(aiMovementMock.getInputsBasedOnAStar()).thenReturn(inputs);
        when(SPIlocator.getSpIlocator().getMovement()).thenReturn(new Movement());

        EnemyMovement enemyMovement = new EnemyMovement();
        enemyMovement.aiMovement = aiMovementMock;

        // Act
        enemyMovement.process(inputs, gameDataMock);

        // Assert
        verify(SPIlocator.getSpIlocator().getMovement()).defaultMove(inputs, enemyMock, gameDataMock);
        verify(enemyMock.getSprite()).freshRotate(0.0, 0.0);
    }
}
