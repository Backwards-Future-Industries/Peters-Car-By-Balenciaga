import abstractClasses.Entity;
import interfaces.IBulletService;
import interfaces.IDrawable;
import interfaces.IPlugin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import player.Player;
import player.PlayerControlSystem;
import player.PlayerPlugin;
import utilities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.when;

public class PlayerPluginTest {

    private IPlugin iPlugin;
    private Player player;

    private PlayerControlSystem playerControlSystem;
    @Mock
    private GameData gameData;
    @Mock
    private SPILocator spiLocator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.iPlugin = new PlayerPlugin();
        this.player = new Player();
        this.playerControlSystem= new PlayerControlSystem();

        try ( MockedStatic<SPILocator> spiLocatorMockedStatic = Mockito.mockStatic(SPILocator.class)){
            spiLocatorMockedStatic.when(SPILocator::getSpIlocator).thenReturn(spiLocator);
        }


    }

    @Test
    public void testCreation() {
        //Act
        this.player = (Player) iPlugin.create();

        //Assert
        Assertions.assertEquals(player.getType(), Type.PLAYER);
    }

    @Test
    public void testDelete() {
        //Arrange
        IPlugin iPlugin = new PlayerPlugin();
        GameData gameData = new GameData();

        //Act
        gameData.addNewEntity(iPlugin.create());
        iPlugin.delete(gameData, gameData.getEntityList(Type.PLAYER).getFirst());

        //Assert
        Assertions.assertTrue(gameData.getEntityList(Type.PLAYER).isEmpty());
    }

    @Test
    public void testColideFunction(){
        //Arrange
        Entity entity = new Player();

        //Act
        this.player.setHealth(1);
        entity.setType(Type.OBSTACLE);
        this.player.onCollision(entity);

        //Assert
        Assertions.assertEquals(0,player.getHealth());
    }

    @Test
    public void testSpawnBullet(){
        //Assert
        Assertions.assertTrue(playerControlSystem.shoot(new ArrayList<>(List.of(Inputs.KEY_SPACE)),this.gameData));
    }

    @Test
    public void testShootDelay(){
        //Act
        for(int i = 0; i < 5; i++){
            playerControlSystem.shoot(new ArrayList<>(List.of(Inputs.KEY_SPACE)),this.gameData);
        }

        //Assert
        Assertions.assertFalse(playerControlSystem.shoot(new ArrayList<>(List.of(Inputs.KEY_SPACE)),this.gameData));
    }
}



