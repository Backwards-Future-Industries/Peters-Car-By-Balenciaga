import abstractClasses.CommonMap;
import abstractClasses.Entity;
import enemy.Enemy;
import enemy.aiMovement.AIMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import utilities.GameData;
import utilities.Inputs;
import utilities.Type;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class AIMovementTest {

    private AIMovement aIMovement;
    private Enemy enemy;
    @Mock
    private Entity player;
    @Mock
    private GameData gameData;
    @Mock
    private CommonMap map;

    @BeforeEach
    void setUp() {
        //Arrange
        MockitoAnnotations.initMocks(this);
        enemy = new Enemy();
        aIMovement = new AIMovement();
        when(gameData.getMap()).thenReturn(map);
        when(map.getAiMap()).thenReturn(new int[][]{
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1},
        });
        when(player.getPosition()).thenReturn(new Point(30, 1));
        when(player.getType()).thenReturn(Type.PLAYER);
        LinkedList<Entity> playerList = new LinkedList<>();
        playerList.add(player);
        when(gameData.getEntityList(Type.PLAYER)).thenReturn(playerList);

        //Act
        enemy.setPosition(new Point(1, 1));
    }

    @Test
    void getInputsBasedOnAStar() {
        //Arrange
        ArrayList<Inputs> inputs = new ArrayList<>();

        //Act
        aIMovement.updateData(gameData, enemy);
        inputs = aIMovement.getInputsBasedOnAStar();

        //Assert
        assertTrue(inputs.contains(Inputs.KEY_W));
    }
}