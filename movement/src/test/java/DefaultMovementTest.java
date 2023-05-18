import abstractClasses.Entity;
import movement.DefaultMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.GameData;
import utilities.Inputs;
import utilities.Vector2D;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


class DefaultMovementTest {
    private static Vector2D expectedDirection;
    private static int[] expectedPosition;
    private int[] position;
    private static Entity testEntity;
    private static DefaultMovement movement;
    private ArrayList<Inputs> inputs;
    private double radians;
    private GameData gameData;

    @BeforeEach
    void setup(){
        URL sprite = DefaultMovement.class.getClassLoader().getResource("images/placeholder.png");
        testEntity = new ConcreteEntity();
        testEntity.setHealth(5);
        testEntity.setSprite(sprite,new double[]{1,1},true);
        testEntity.setMaxSpeed(10);
        testEntity.setAcceleration(1);
        expectedPosition = new int[]{1,0};
        expectedDirection = new Vector2D(0,0);
        this.inputs = new ArrayList<>();
        movement = new DefaultMovement();
        gameData = new GameData();
        gameData.setScreenSize(new Dimension(1000,1000));

    }

    @Test
    void defaultMoveW() {
        inputs.add(Inputs.KEY_W);
        expectedDirection.setX(1);
        expectedDirection.setY(0);
        expectedPosition = new int[]{1, 0};
        position = movement.defaultMove(inputs,testEntity,gameData);
        assertArrayEquals(position,expectedPosition);
        assertEquals(expectedDirection.getX(),testEntity.getDirection().getX());
        assertEquals(expectedDirection.getY(),testEntity.getDirection().getY());
    }

    @Test
    void defaultMoveS(){
        inputs.add(Inputs.KEY_S);
        expectedDirection.setX(-0.5);
        expectedDirection.setY(0);
        expectedPosition = new int[]{0, 0};
        position = movement.defaultMove(inputs,testEntity,gameData);
        assertArrayEquals(position,expectedPosition);
        assertEquals(expectedDirection.getX(),testEntity.getDirection().getX());
        assertEquals(expectedDirection.getY(),testEntity.getDirection().getY());
    }

    @Test
    void defaultMoveA(){
        inputs.add(Inputs.KEY_W);
        inputs.add(Inputs.KEY_A);
        expectedPosition = new int[]{1, 0};
        radians = 6.2733678301371185;
        position = movement.defaultMove(inputs,testEntity,gameData);
        assertArrayEquals(position,expectedPosition);
        assertEquals(radians,testEntity.getRadians());
    }

    @Test
    void defaultMoveD(){
        inputs.add(Inputs.KEY_W);
        inputs.add(Inputs.KEY_D);
        expectedPosition = new int[]{1, 0};
        radians = 0.009817477042468103;
        position = movement.defaultMove(inputs,testEntity,gameData);
        assertArrayEquals(position,expectedPosition);
        assertEquals(radians,testEntity.getRadians());
    }
}