import abstractClasses.Entity;
import enemy.EnemyPlugin;
import enemy.aiMovement.AIMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.GameData;
import utilities.Inputs;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AIMovementTest {

    private AIMovement aIMovement;
    private GameData gameData;
    private Entity enemyPlugin;
    private Entity playerPlugin;

    @BeforeEach
    void setUp() {
        URL sprite = AIMovementTest.class.getClassLoader().getResource("images/placeholder.png");
        playerPlugin = new concreteEntity(1, sprite);
        enemyPlugin = new EnemyPlugin();
        enemyPlugin.setMaxSpeed(10);
        enemyPlugin.setPosition(new int[]{100, 200});
        gameData = new GameData();
        aIMovement = new AIMovement();
    }

    @Test
    void getInputsBasedOnAStar() {
        playerPlugin.setPosition(new int[]{500, 200});
        gameData.addNewEntity(playerPlugin);
        aIMovement.updateData(gameData, enemyPlugin);
        assertTrue(aIMovement.getInputsBasedOnAStar().contains(Inputs.KEY_W));
    }
}