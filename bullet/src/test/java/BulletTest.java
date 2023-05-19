import abstractClasses.Entity;
import grillBullet.BulletPlugin;
import interfaces.IBulletService;
import org.junit.jupiter.api.Test;
import utilities.Vector2D;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BulletTest {

    @Test
    void testVariables() {
        //Arrange
        IBulletService iBulletService = new BulletPlugin();
        int[] position = {2, 4};
        Vector2D direction = new Vector2D(20,50);

        //Act
        Entity bullet = iBulletService.create(new int[]{2,4},2.5);
        bullet.setHealth(1);
        bullet.setAcceleration(1);
        bullet.setMaxSpeed(3);
        bullet.setDirection(new Vector2D(20,50));

        //Assert
        assertArrayEquals(position, bullet.getPosition());
        assertArrayEquals(direction.getComponents(), bullet.getDirection().getComponents());
        assertEquals(bullet.getHealth(), 1);
        assertEquals(bullet.getAcceleration(), 1);
        assertEquals(bullet.getMaxSpeed(), 3);
    }

}