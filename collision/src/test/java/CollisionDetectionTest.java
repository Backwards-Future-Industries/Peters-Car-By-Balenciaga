import abstractClasses.Entity;
import collision.CollisionDetection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilities.Type;

import java.net.URL;

class CollisionDetectionTest {

    CollisionDetection collisionDetection = new CollisionDetection();
    private static URL sprite = TestEntity.class.getClassLoader().getResource("images/placeholder.png");
    private class TestEntity extends Entity {

        public TestEntity(int health, URL sprite) {
            super(health, sprite, Type.UNDEFINED);
        }

    }

    TestEntity testEntity1 = new TestEntity(1,sprite);
    TestEntity testEntity2 = new TestEntity(1,sprite);




}
