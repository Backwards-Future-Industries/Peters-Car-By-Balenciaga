import abstractClasses.Entity;
import collision.CollisionDetection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URL;

class CollisionDetectionTest {

    CollisionDetection collisionDetection = new CollisionDetection();
    private static URL sprite = TestEntity.class.getClassLoader().getResource("images/placeholder.png");
    private class TestEntity extends Entity {

        public TestEntity(int health, URL sprite) {
            super(health, sprite);
        }

    }

    TestEntity testEntity1 = new TestEntity(1,sprite);
    TestEntity testEntity2 = new TestEntity(1,sprite);

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