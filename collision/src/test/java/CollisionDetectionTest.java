import abstractClasses.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

class CollisionDetectionTest {

    CollisionDetection collisionDetection = new CollisionDetection();

    private class TestEntity extends Entity {

        public TestEntity(int health, BufferedImage sprite) {
            super(health, sprite);
        }

    }

    TestEntity testEntity1 = new TestEntity(1,null);
    TestEntity testEntity2 = new TestEntity(1,null);

    @Test
    public void colliding(){

         testEntity1.setPosition(new int[]{400,400});
         testEntity2.setPosition(new int[]{400,439});
         Assertions.assertTrue(collisionDetection.isColliding(testEntity1,testEntity2));
    }

    @Test
    public void notColliding(){
        testEntity1.setPosition(new int[]{400,400});
        testEntity2.setPosition(new int[]{400,440});
        Assertions.assertFalse(collisionDetection.isColliding(testEntity1,testEntity2));
    }



}