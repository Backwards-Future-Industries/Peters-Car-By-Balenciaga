import abstractClasses.Entity;
import collision.CollisionDetection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilities.Type;

import java.net.URL;

class CollisionDetectionTest {

    CollisionDetection collisionDetection = new CollisionDetection();

    //This placeholder sprite is 480x288
    private static URL sprite = TestEntity.class.getClassLoader().getResource("images/placeholder.png");
    private class TestEntity extends Entity {

        public TestEntity(int health, URL sprite) {
            super(health, sprite, Types.WEAPON);
        }

    }

    TestEntity testEntity1 = new TestEntity(1,sprite);
    TestEntity testEntity2 = new TestEntity(1,sprite);

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
