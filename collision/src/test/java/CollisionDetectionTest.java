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
    private static URL sprite = concreteEntity.class.getClassLoader().getResource("images/placeholder.png");

    Entity testEntity1;
    Entity testEntity2;

    @BeforeEach
    public void setup(){
        this.testEntity1 = new concreteEntity();
        this.testEntity2 = new concreteEntity();
        testEntity1.setHealth(1);
        testEntity2.setHealth(1);
        testEntity1.setSprite(sprite, new double[]{1,1},true);
        testEntity2.setSprite(sprite, new double[]{1,1},true);
    }

    @Disabled
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
