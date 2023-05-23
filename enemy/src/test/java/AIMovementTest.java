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
        enemy.setPosition(new Point(1, 1));
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
    }

    @Test
    void getInputsBasedOnAStar() {
        aIMovement.updateData(gameData, enemy);
        ArrayList<Inputs> inputs = aIMovement.getInputsBasedOnAStar();
        assertTrue(inputs.contains(Inputs.KEY_W));
    }
}