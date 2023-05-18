import abstractClasses.Entity;
import collision.CollisionDetection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utilities.Type;

import java.net.URL;

class CollisionDetectionTest {

    CollisionDetection collisionDetection = new CollisionDetection();
    //Placeholder is 480x288
    private static URL sprite = ConcreteEntity.class.getClassLoader().getResource("images/placeholder.png");

    Entity testEntity1;
    Entity testEntity2;

    @BeforeEach
    public void setup(){
        this.testEntity1 = new ConcreteEntity();
        this.testEntity2 = new ConcreteEntity();
        testEntity1.setHealth(1);
        testEntity2.setHealth(1);
        testEntity1.setSprite(sprite, new double[]{1,1},true);
        testEntity2.setSprite(sprite, new double[]{1,1},true);
        testEntity1.setType(Type.PLAYER);
        testEntity2.setType(Type.ENEMY);
    }


    @Disabled
    @Test
    void testCollisionDetection() {
        testEntity1.setPosition(new int[]{0, 0});
        testEntity2.setPosition(new int[]{479, 0});
        Assertions.assertTrue(collisionDetection.isColliding(testEntity1, testEntity2));
    }

    @Test
    void testNoCollision() {
        testEntity1.setPosition(new int[]{0, 0});
        testEntity2.setPosition(new int[]{480, 0});
        Assertions.assertFalse(collisionDetection.isColliding(testEntity1, testEntity2));
    }

}
