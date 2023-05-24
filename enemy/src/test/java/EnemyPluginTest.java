import abstractClasses.Entity;
import enemy.Enemy;
import enemy.EnemyControlSystem;
import enemy.EnemyPlugin;
import interfaces.IPlugin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import utilities.GameData;
import utilities.Inputs;
import utilities.SPILocator;
import utilities.Type;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class EnemyPluginTest {
    private Enemy enemy;
    private IPlugin iPlugin;

    private EnemyControlSystem enemyControlSystem;

    @Mock
    private SPILocator spiLocator;
    private GameData gameData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.enemy = new Enemy();
        this.iPlugin = new EnemyPlugin();
        this.enemyControlSystem = new EnemyControlSystem();
        this.gameData = new GameData();

        try ( MockedStatic<SPILocator> spiLocatorMockedStatic = Mockito.mockStatic(SPILocator.class)){
            spiLocatorMockedStatic.when(SPILocator::getSpIlocator).thenReturn(spiLocator);
        }
    }


    @Test
    public void testCreation() {
        //Act
        enemy = (Enemy) iPlugin.create();

        //Assert
        Assertions.assertEquals(enemy.getType(), Type.ENEMY);
    }

    @Test
    public void testDelete() {
        //Arrange
        IPlugin iPlugin = new EnemyPlugin();
        GameData gameData = new GameData();

        //Act
        gameData.addNewEntity(iPlugin.create());
        iPlugin.delete(gameData, gameData.getEntityList(Type.ENEMY).getFirst());

        //Assert
        Assertions.assertTrue(gameData.getEntityList(Type.ENEMY).isEmpty());
    }

    @Test
    public void testColideFunction(){
        //Arrange
        Entity entity = new Enemy();

        //Act
        enemy.setHealth(1);
        entity.setType(Type.BULLET);
        enemy.onCollision(entity);

        //Assert
        Assertions.assertEquals(0,enemy.getHealth());
    }

    @Test
    public void testSpawnBullet(){
        //Assert
        Assertions.assertTrue(enemyControlSystem.shoot(this.gameData, this.enemy,new ArrayList<>(List.of(Inputs.KEY_SPACE))));
    }
}