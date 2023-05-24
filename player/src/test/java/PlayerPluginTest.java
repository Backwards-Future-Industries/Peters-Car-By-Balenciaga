import abstractClasses.Entity;
import interfaces.IPlugin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import player.Player;
import player.PlayerPlugin;
import utilities.GameData;
import utilities.Type;

public class PlayerPluginTest {

    private IPlugin iPlugin;
    private Player player;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        iPlugin = new PlayerPlugin();
        player = new Player();
    }

    @Test
    public void testCreation() {
        //Act
        player = (Player) iPlugin.create();

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
        player.setHealth(1);
        entity.setType(Type.OBSTACLE);
        player.onCollision(entity);

        //Assert
        Assertions.assertEquals(0,player.getHealth());
    }
}



