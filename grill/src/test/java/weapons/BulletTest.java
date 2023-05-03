package weapons;

import abstractClasses.Entity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.Vector2D;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BulletTest {

    private Entity bullet;

    private int[] position = {20, 50};

    private Vector2D direction = new Vector2D(2,4);
    @BeforeEach
    void setUp() throws IOException {
        this.bullet = new Bullet(position, direction);
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