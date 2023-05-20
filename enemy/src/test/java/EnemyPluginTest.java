import abstractClasses.Entity;
import enemy.Enemy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

class EnemyPluginTest {
    private Point positions;
    private Entity enemy;

    @BeforeEach
    void setUp() {
        this.enemy = new Enemy();
        this.enemy.setPosition(new Point(1, 1));
        this.positions = new Point(1, 1);
    }

    @Test
    void testPosition() {
        Assertions.assertEquals(positions, enemy.getPosition());
    }
}