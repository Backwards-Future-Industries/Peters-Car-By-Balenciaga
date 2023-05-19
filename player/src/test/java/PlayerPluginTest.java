import abstractClasses.Entity;
import interfaces.IPlugin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import player.Player;
import player.PlayerPlugin;
import utilities.GameData;
import utilities.Type;
import utilities.Vector2D;

import java.util.LinkedList;

public class PlayerPluginTest {
    private int[] position;
    private Entity playerPlugin;
    private Entity entity;
    private IPlugin iPlugin;
    @Mock
    private GameData gameData;
    private LinkedList<IPlugin> entityMap;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.gameData = new GameData();
        this.iPlugin = new PlayerPlugin();
        this.entityMap = new LinkedList<>();
        this.entity = iPlugin.create();
        this.gameData.addNewEntity(entity);
        this.playerPlugin = new Player();
        this.playerPlugin.setPosition(new int[]{700, 500});
        this.position = new int[]{700, 500};
    }

    @Test
    public void testPosition() {
        Assertions.assertEquals(this.position[0], playerPlugin.getPosition()[0]);
        Assertions.assertEquals(this.position[1], playerPlugin.getPosition()[1]);
    }

    @Test
    public void testCreation() {
        Assertions.assertEquals(this.entity.getType(), Type.PLAYER);
        Assertions.assertEquals(this.entity.getDirection().getClass(), Vector2D.class);
    }

    @Test
    public void testDelete(){
       Assertions.assertEquals(gameData.getEntityList(Type.PLAYER).get(0).getType(),Type.PLAYER);
       this.iPlugin.delete(gameData,gameData.getEntityList(Type.PLAYER).get(0));
        Assertions.assertEquals( this.entityMap,gameData.getEntityList(Type.PLAYER));
    }
}



