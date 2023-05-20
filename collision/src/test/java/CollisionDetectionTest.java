import abstractClasses.Entity;
import collision.CollisionDetection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utilities.Type;
import utilities.image.ImageLoader;

import java.awt.*;
import java.net.URL;

import static org.mockito.Mockito.when;

class CollisionDetectionTest {

    @Mock
    private Entity testEntity1;
    @Mock
    private Entity testEntity2;

    CollisionDetection collisionDetection = new CollisionDetection();
    //Placeholder is 480x288
    private static URL sprite = CollisionDetectionTest.class.getClassLoader().getResource("images/placeholder.png");

    @BeforeEach
    public void setup() {
        //Arrange
        MockitoAnnotations.initMocks(this);
        when(this.testEntity1.getSprite()).thenReturn(ImageLoader.loadImage(sprite, new double[]{1, 1}));
        when(this.testEntity2.getSprite()).thenReturn(ImageLoader.loadImage(sprite, new double[]{1, 1}));
        when(this.testEntity1.getPosition()).thenReturn(new Point(0, 0));

        //Act
        testEntity1.setHealth(1);
        testEntity2.setHealth(1);
        testEntity1.setSprite(sprite, new double[]{1, 1});
        testEntity2.setSprite(sprite, new double[]{1, 1});
        testEntity1.setType(Type.PLAYER);
        testEntity2.setType(Type.ENEMY);
    }

    @Test
    void testCollisionDetection() {
        //Act
        testEntity1.getSprite().redoSourceRectangle(0, 0);
        when(this.testEntity2.getPosition()).thenReturn(new Point(479, 0));
        testEntity2.getSprite().redoSourceRectangle(479, 0);

        //Assert
        Assertions.assertTrue(collisionDetection.isColliding(testEntity1, testEntity2));
    }

    @Test
    void testNoCollision() {
        //Act
        testEntity1.getSprite().redoSourceRectangle(0, 0);
        when(this.testEntity2.getPosition()).thenReturn(new Point(0, 289));
        testEntity2.getSprite().redoSourceRectangle(0, 289);

        //Assert
        Assertions.assertFalse(collisionDetection.isColliding(testEntity1, testEntity2));
    }

}
