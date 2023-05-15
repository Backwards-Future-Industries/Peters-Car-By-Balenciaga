import abstractClasses.Entity;
import grillBullet.Bullet;
import interfaces.IPlugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.Vector2D;

import static org.junit.jupiter.api.Assertions.*;

class BulletTest {

    private Entity bullet;

    private final int[] position = {20, 50};

    private final Vector2D direction = new Vector2D(2,4);
    @BeforeEach
    void setUp() {
        this.bullet = new Bullet();
    }

    @Test
    void testVariables() {
        bullet.setPosition(new int[]{20,50});
        assertArrayEquals(this.position, bullet.getPosition());
        this.bullet.setDirection(new Vector2D(2,4));
        assertArrayEquals(this.direction.getComponents(), bullet.getDirection().getComponents());
        bullet.setHealth(1);
        assertEquals(bullet.getHealth(), 1);
        bullet.setAcceleration(1);
        assertEquals(bullet.getAcceleration(), 1);
        bullet.setMaxSpeed(3);
        assertEquals(bullet.getMaxSpeed(), 3);
    }

}