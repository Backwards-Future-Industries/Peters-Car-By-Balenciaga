import abstractClasses.CommonMap;
import abstractClasses.Entity;
import enemy.EnemyPlugin;
import enemy.aiMovement.AIMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utilities.GameData;
import utilities.Inputs;
import utilities.Type;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AIMovementTest {

    private AIMovement aIMovement;
    private Entity enemyPlugin;
    private Entity playerPlugin;
    @Mock
    private GameData gameData;
    @Mock
    private CommonMap map;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        playerPlugin = new ConcreteEntity();
        enemyPlugin = new EnemyPlugin();
        enemyPlugin.setPosition(new int[]{1, 1});
        gameData = new GameData();
        aIMovement = new AIMovement();
        map = new concreteMap();
        map.setAiMap(new int[][]{
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
        });
        gameData.setMap(map);
    }

    @Test
    void getInputsBasedOnAStar() {
        playerPlugin.setPosition(new int[]{65, 1});
        playerPlugin.setType(Type.PLAYER);
        gameData.addNewEntity(playerPlugin);
        aIMovement.updateData(gameData, enemyPlugin);
        ArrayList<Inputs> inputs = aIMovement.getInputsBasedOnAStar();
        assertTrue(inputs.contains(Inputs.KEY_W));
    }
}