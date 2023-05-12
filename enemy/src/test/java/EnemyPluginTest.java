import abstractClasses.Entity;
import enemy.Enemy;
import enemy.EnemyPlugin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.image.Image;
import utilities.image.ImageLoader;

import java.io.IOException;
import java.net.URL;

class EnemyPluginTest {
    private int[] positions;
    private Entity lowTierGod;
    @BeforeEach
    void setUp() {
        this.lowTierGod = new Enemy();
        this.lowTierGod.setPosition(new int[]{1,1});
        this.positions = new int[]{1,1};
    }

    @Test
    void testPosition() {
        Assertions.assertArrayEquals(positions,lowTierGod.getPosition());
    }
}