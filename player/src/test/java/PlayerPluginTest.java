import abstractClasses.Entity;
import interfaces.IPlugin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Player;
import player.PlayerPlugin;
import utilities.GameData;
import utilities.Type;
import utilities.Vector2D;

import java.util.LinkedList;

public class PlayerPluginTest {
    private GameData gameData;

    @BeforeEach
    void setUp() {
        this.gameData = new GameData();


    }

    @Test
    public void testPosition() {
        //Arrange
        Entity playerPlugin = new Player();

        //Act
        playerPlugin.setPosition(new int[]{700, 500});
        int[] position = new int[]{700, 500};

        //Assert
        Assertions.assertEquals(position[0], playerPlugin.getPosition()[0]);
        Assertions.assertEquals(position[1], playerPlugin.getPosition()[1]);
    }

    @Test
    public void testCreation() {
        //Arrange
        IPlugin iPlugin = new PlayerPlugin();

        //Act
        Entity entity = iPlugin.create();

        //Assert
        Assertions.assertEquals(entity.getType(), Type.PLAYER);
        Assertions.assertEquals(entity.getDirection().getClass(), Vector2D.class);
    }

    @Test
    public void testDelete() {
        //Arrange
        LinkedList<Entity> entityMap = new LinkedList<>();
        IPlugin iPlugin = new PlayerPlugin()
        ;

        //Act
        this.gameData.addNewEntity(iPlugin.create());
        iPlugin.delete(gameData, gameData.getEntityList(Type.PLAYER).get(0));

        //Assert
        Assertions.assertEquals(entityMap, gameData.getEntityList(Type.PLAYER));
    }
}



