package gameEngine;

import abstractClasses.Entity;
import interfaces.IProcessing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utilities.GameData;
import utilities.Type;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.condition.OS.LINUX;
import static org.junit.jupiter.api.condition.OS.WINDOWS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameEngineTest {

    //Arrange
    private GameEngine gameEngine;
    private Dimension dimension;
    @Mock
    private GameData gameData;

    private Map<Type, LinkedList<Entity>> entityMap;

    @BeforeEach
    void setUp() {
        //Arrange
        MockitoAnnotations.initMocks(this);
        this.dimension = new Dimension(500,700);
        this.gameEngine = new GameEngine(60, gameData, dimension);
        this.entityMap = new HashMap<>();
    }

    @EnabledOnOs(LINUX)
    @Test
    public void OpenWindowTestOnLinux_fr4(){
        //Assert
        Assertions.assertEquals("Peter's car",gameEngine.getWindow().getTitle());
        Assertions.assertEquals(dimension.width ,gameEngine.getWindow().getWidth());
        Assertions.assertEquals(1,gameEngine.getWindow().getKeyListeners().length);
        Assertions.assertTrue(gameEngine.getWindow().isVisible());
    }

    @EnabledOnOs(WINDOWS)
    @Test
    public void OpenWindowTestOnWindows_fr4(){
        //Assert
        Assertions.assertEquals("Peter's car",gameEngine.getWindow().getTitle());
        Assertions.assertEquals(dimension.width ,gameEngine.getWindow().getWidth()-16);
        Assertions.assertEquals(1,gameEngine.getWindow().getKeyListeners().length);
        Assertions.assertTrue(gameEngine.getWindow().isVisible());
    }

    @Test
    public void accessToAllPositions(){
        //Arrange
        Entity enemy = mock(Entity.class);
        when(enemy.getPosition()).thenReturn(new Point(5,5));
        Entity enemy2 = mock(Entity.class);
        when(enemy2.getPosition()).thenReturn(new Point(10,10));
        Entity player = mock(Entity.class);
        when(player.getPosition()).thenReturn(new Point(5,5));
        Entity player2 = mock(Entity.class);
        when(player2.getPosition()).thenReturn(new Point(10,10));

        entityMap.put(Type.ENEMY,new LinkedList<Entity>());
        entityMap.put(Type.PLAYER, new LinkedList<Entity>());
        entityMap.get(Type.ENEMY).add(enemy);
        entityMap.get(Type.ENEMY).add(enemy2);
        entityMap.get(Type.PLAYER).add(player);
        entityMap.get(Type.PLAYER).add(player2);

        for (var entry : entityMap.entrySet()){
            for(Entity entity : entry.getValue()){
                assertDoesNotThrow(() ->
                        entity.getPosition()
                );
            }
        }
    }
}
