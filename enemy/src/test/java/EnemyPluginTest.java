import enemy.EnemyPlugin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.image.Image;
import utilities.image.ImageLoader;

import java.io.IOException;
import java.net.URL;

class EnemyPluginTest {
    private EnemyPlugin lowTierGod;
    private double[] positions;
    private Image lowTierGodImage;
    @BeforeEach
    void setUp() {
        positions = new double[]{10, 10};
        URL url = EnemyPluginTest.class.getClassLoader().getResource("images/ltg.png");
            lowTierGodImage = ImageLoader.loadImage(url, new double[]{1,1});
            lowTierGod.create();
            lowTierGod.create();

    }

    @Test
    void create() {
        Assertions.assertNotEquals(positions,lowTierGod.getPosition());
        Assertions.assertEquals(10,lowTierGod.getHealth());
        Assertions.assertTrue(ImageLoader.Comparator(lowTierGodImage.getImage(),lowTierGod.getSprite().getImage()));
    }

    @Test
    void delete() {
        Assertions.assertEquals(0,lowTierGod.delete().getHealth());
    }
}