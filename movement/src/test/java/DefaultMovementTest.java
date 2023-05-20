import abstractClasses.Entity;
import movement.DefaultMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utilities.GameData;
import utilities.Inputs;
import utilities.Vector2D;
import utilities.image.ImageLoader;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@Disabled
class DefaultMovementTest {
    private static Vector2D expectedDirection;
    private static Point expectedPosition;
    private Point position;
    private static DefaultMovement movement;
    private ArrayList<Inputs> inputs;
    private double radians;

    @Mock
    private static Entity testEntity;
    @Mock
    private GameData gameData;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        URL sprite = DefaultMovement.class.getClassLoader().getResource("images/placeholder.png");
        when(testEntity.getSprite()).thenReturn(ImageLoader.loadImage(sprite, new double[]{1, 1}));
        when(testEntity.getMaxSpeed()).thenReturn(10.);
        when(testEntity.getAcceleration()).thenReturn(1.);
        when(testEntity.getPosition()).thenReturn(new Point(0, 0));
        when(testEntity.getRadians()).thenReturn(0.);
        expectedPosition = new Point(1, 0);
        expectedDirection = new Vector2D(0, 0);
        when(testEntity.getDirection()).thenReturn(new Vector2D(0,0));
        this.inputs = new ArrayList<>();
        movement = new DefaultMovement();
        when(gameData.getScreenSize()).thenReturn(new Dimension(1000,1000));
    }

    @Test
    void defaultMoveW() {
        inputs.add(Inputs.KEY_W);
        expectedDirection.setX(1);
        expectedDirection.setY(0);
        when(testEntity.getDirection()).thenReturn(new Vector2D(1,0));
        position = movement.defaultMove(inputs, testEntity, gameData);
        assertEquals(position, expectedPosition);
        assertEquals(expectedDirection.getX(), testEntity.getDirection().getX());
        assertEquals(expectedDirection.getY(), testEntity.getDirection().getY());
    }

    @Test
    void defaultMoveS() {
        inputs.add(Inputs.KEY_S);
        expectedDirection.setX(-0.5);
        expectedDirection.setY(0);
        expectedPosition = new Point(0, 0);
        when(testEntity.getDirection()).thenReturn(new Vector2D(-0.5,0));
        position = movement.defaultMove(inputs, testEntity, gameData);
        assertEquals(position, expectedPosition);
        assertEquals(expectedDirection.getX(), testEntity.getDirection().getX());
        assertEquals(expectedDirection.getY(), testEntity.getDirection().getY());
    }

    @Test
    void defaultMoveA() {
        inputs.add(Inputs.KEY_W);
        inputs.add(Inputs.KEY_A);
        radians = 6.2733678301371185;
        position = movement.defaultMove(inputs, testEntity, gameData);
        assertEquals(position, expectedPosition);
        assertEquals(radians, testEntity.getRadians());
    }

    @Test
    void defaultMoveD() {
        inputs.add(Inputs.KEY_W);
        inputs.add(Inputs.KEY_D);
        radians = 0.009817477042468103;
        position = movement.defaultMove(inputs, testEntity, gameData);
        assertEquals(position, expectedPosition);
        assertEquals(radians, testEntity.getRadians());
    }
}