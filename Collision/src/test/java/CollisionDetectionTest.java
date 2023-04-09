import abstractClasses.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

class CollisionDetectionTest {

    CollisionDetection collisionDetection = new CollisionDetection();

    private class TestEntity extends Entity {

        public TestEntity(int health, BufferedImage sprite) {
            super(health, sprite);
        }

        @Override
        public void setPosition(double positionX, double positionY) {
            super.setPosition(positionX, positionY);
        }
    }

    TestEntity testEntity1 = new TestEntity(1,null);
    TestEntity testEntity2 = new TestEntity(1,null);

    @Test
    public void colliding(){
         testEntity1.setPosition(400,400);
         testEntity2.setPosition(400,439);
         Assertions.assertTrue(collisionDetection.isColliding(testEntity1,testEntity2));
    }

    @Test
    public void notColliding(){
        testEntity1.setPosition(400,400);
        testEntity2.setPosition(400,440);
        Assertions.assertFalse(collisionDetection.isColliding(testEntity1,testEntity2));
    }



}