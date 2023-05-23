import abstractClasses.Entity;
import movement.DefaultMovement;
import org.junit.jupiter.api.BeforeEach;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DefaultMovementTest {
    private Vector2D expectedDirection;
    private Point expectedPosition;
    private Point position;
    private DefaultMovement movement;
    private ArrayList<Inputs> inputs;

    @Mock
    private Entity testEntity;
    @Mock
    private GameData gameData;

    @BeforeEach
    void setup() {
        //Arrange
        MockitoAnnotations.initMocks(this);
        URL sprite = DefaultMovement.class.getClassLoader().getResource("images/placeholder.png");
        when(testEntity.getSprite()).thenReturn(ImageLoader.loadImage(sprite, new double[]{1, 1}));
        when(testEntity.getMaxSpeed()).thenReturn(10.);
        when(testEntity.getAcceleration()).thenReturn(1.);
        when(testEntity.getPosition()).thenReturn(new Point(0, 0));
        when(testEntity.getDirection()).thenReturn(new Vector2D(0, 0));
        when(gameData.getScreenSize()).thenReturn(new Dimension(1000, 1000));
        expectedPosition = new Point(1, 0);
        expectedDirection = new Vector2D(0, 0);
        this.inputs = new ArrayList<>();
        movement = new DefaultMovement();
    }

    @Test
    void defaultMoveW() {
        //Arrange
        when(testEntity.getDirection()).thenReturn(new Vector2D(0, 0));

        //Act
        inputs.add(Inputs.KEY_W);
        expectedDirection.setX(1);
        expectedDirection.setY(0);
        position = movement.defaultMove(inputs, testEntity, gameData);

        //Assert
        assertEquals(expectedPosition, position);
        assertEquals(expectedDirection.getX(), testEntity.getDirection().getX());
        assertEquals(expectedDirection.getY(), testEntity.getDirection().getY());
    }

    @Test
    void defaultMoveS() {
        //Arrange
        when(testEntity.getDirection()).thenReturn(new Vector2D(0, 0));

        //Act
        inputs.add(Inputs.KEY_S);
        expectedDirection.setX(-0.5);
        expectedDirection.setY(0);
        expectedPosition = new Point(0, 0);
        position = movement.defaultMove(inputs, testEntity, gameData);

        //Assert
        assertEquals(expectedDirection.getX(), testEntity.getDirection().getX());
        assertEquals(expectedDirection.getY(), testEntity.getDirection().getY());
    }

    @Test
    void defaultMoveA() {
        //Act
        inputs.add(Inputs.KEY_W);
        inputs.add(Inputs.KEY_A);
        expectedDirection = new Vector2D(0.999951808959328, -0.00981684623031415);
        position = movement.defaultMove(inputs, testEntity, gameData);

        //Assert
        assertArrayEquals(expectedDirection.getComponents(),testEntity.getDirection().getComponents());
    }

    @Test
    void defaultMoveD() {
        //Act
        inputs.add(Inputs.KEY_W);
        inputs.add(Inputs.KEY_D);
        expectedDirection = new Vector2D(0.999951808959328, 0.00981684623031415);
        position = movement.defaultMove(inputs, testEntity, gameData);

        //Assert
        assertArrayEquals(expectedDirection.getComponents(), testEntity.getDirection().getComponents());
    }
}