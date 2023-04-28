import abstractClasses.Entity;
import interfaces.IGameEngine;
import interfaces.IMovement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.Inputs;
import utilities.Vector2D;

import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


class IMovementTest {
    private static Vector2D expectedDirection;
    private static int[] expectedPosition;
    private int[] position;
    private static Entity testEntity;
    private static IMovement movement;
    private ArrayList<Inputs> inputs;
    private double radians;
    @BeforeEach
    void setup(){
        URL sprite = EntityTest.class.getClassLoader().getResource("images/placeholder.png");
        testEntity = new concreteEntity(5, sprite);
        movement = new IMovement() {
            @Override
            public int[] defaultMove(ArrayList<Inputs> inputs, Entity entity, IGameEngine gameEngine) {
                return IMovement.super.defaultMove(inputs, entity,gameEngine);
            }
        };

        expectedPosition = new int[]{1,0};
        expectedDirection = new Vector2D(0,0);
        this.inputs = new ArrayList<>();
    }

    @Test
    void defaultMoveW() {
        inputs.add(Inputs.KEY_W);
        expectedDirection.setX(1);
        expectedDirection.setY(0);
        expectedPosition = new int[]{1, 0};
        position = movement.defaultMove(inputs,testEntity);
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
        position = movement.defaultMove(inputs,testEntity);
        assertArrayEquals(position,expectedPosition);
        assertEquals(expectedDirection.getX(),testEntity.getDirection().getX());
        assertEquals(expectedDirection.getY(),testEntity.getDirection().getY());
    }

    @Test
    void defaultMoveA(){
        inputs.add(Inputs.KEY_A);
        expectedPosition = new int[]{0, 0};
        radians = 6.1850105367549055;
        position = movement.defaultMove(inputs,testEntity);
        assertArrayEquals(position,expectedPosition);
        assertEquals(radians,testEntity.getRadians());
    }

    @Test
    void defaultMoveD(){
        inputs.add(Inputs.KEY_D);
        expectedPosition = new int[]{0, 0};
        radians = 0.09817477042468103;
        position = movement.defaultMove(inputs,testEntity);
        assertArrayEquals(position,expectedPosition);
        assertEquals(radians,testEntity.getRadians());
    }
}