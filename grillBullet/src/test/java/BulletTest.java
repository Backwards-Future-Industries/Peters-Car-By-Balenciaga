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
        assertEquals(this.position, bullet.getPosition());
        assertEquals(this.direction, bullet.getDirection());
        assertEquals(bullet.getHealth(), 1);
        assertEquals(bullet.getAcceleration(), 1);
        assertEquals(bullet.getMaxSpeed(), 3);
    }

}