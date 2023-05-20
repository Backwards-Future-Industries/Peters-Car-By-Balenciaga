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

import java.awt.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class PlayerPluginTest {

    IPlugin iPlugin;
    Player player;

    @BeforeEach
    public void setUp() {
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
        ConcurrentLinkedDeque<Entity> entityMap = new ConcurrentLinkedDeque<>();
        IPlugin iPlugin = new PlayerPlugin();
        GameData gameData = new GameData();

        //Act
        gameData.addNewEntity(iPlugin.create());
        iPlugin.delete(gameData, gameData.getEntityList(Type.PLAYER).getFirst());

        //Assert
        Assertions.assertTrue(gameData.getEntityList(Type.PLAYER).isEmpty());
    }
}



