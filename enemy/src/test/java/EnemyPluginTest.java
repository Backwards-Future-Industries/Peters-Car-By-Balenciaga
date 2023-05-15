import abstractClasses.Entity;
import enemy.Enemy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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