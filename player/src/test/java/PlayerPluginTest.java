import abstractClasses.Entity;
import interfaces.IPlugin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import player.PlayerPlugin;
import utilities.GameData;
import utilities.Type;
import utilities.Vector2D;

import java.util.LinkedList;

public class PlayerPluginTest {


    @Test
    public void testCreation() {
        //Arrange
        IPlugin iPlugin = new PlayerPlugin();
        int[] position = new int[]{700, 500};

        //Act
        Entity entity = iPlugin.create();
        entity.setPosition(new int[]{700, 500});

        //Assert
        Assertions.assertEquals(entity.getType(), Type.PLAYER);
        Assertions.assertEquals(entity.getDirection().getClass(), Vector2D.class);
        Assertions.assertEquals(position[0], entity.getPosition()[0]);
        Assertions.assertEquals(position[1], entity.getPosition()[1]);
    }

    @Test
    public void testDelete() {
        //Arrange
        LinkedList<Entity> entityMap = new LinkedList<>();
        IPlugin iPlugin = new PlayerPlugin();
        GameData gameData = new GameData();

        //Act
        gameData.addNewEntity(iPlugin.create());
        iPlugin.delete(gameData, gameData.getEntityList(Type.PLAYER).get(0));

        //Assert
        Assertions.assertEquals(entityMap,gameData.getEntityList(Type.PLAYER));
    }
}



