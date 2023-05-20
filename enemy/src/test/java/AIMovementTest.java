import abstractClasses.CommonMap;
import abstractClasses.Entity;
import enemy.Enemy;
import enemy.aiMovement.AIMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utilities.GameData;
import utilities.Inputs;
import utilities.Type;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@Disabled
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
        gameData = new GameData();
        aIMovement = new AIMovement();

        when(gameData.getMap()).thenReturn(map);
        when(map.getAiMap()).thenReturn(new int[][]{
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1},
        });
        LinkedList<Entity> enemyList = new LinkedList<>();
        enemyList.add(enemy);
        LinkedList<Entity> playerList = new LinkedList<>();
        playerList.add(player);
        when(gameData.getEntityList(Type.ENEMY)).thenReturn(enemyList);
        when(gameData.getEntityList(Type.PLAYER)).thenReturn(playerList);
    }

    @Test
    void getInputsBasedOnAStar() {
        player.setPosition(new Point(65, 1));
        player.setType(Type.PLAYER);
        gameData.addNewEntity(player);
        aIMovement.updateData(gameData, enemy);
        ArrayList<Inputs> inputs = aIMovement.getInputsBasedOnAStar();
        assertTrue(inputs.contains(Inputs.KEY_W));
    }
}