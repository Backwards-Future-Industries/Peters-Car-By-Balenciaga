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
    private int[] positions;
    private Image lowTierGodImage;
    private static final URL url = EnemyPlugin.class.getResource("/images/ltg.png");

    @BeforeEach
    void setUp() {
        positions = new int[]{10, 10};
        try {
            //lowTierGodImage = ImageLoader.loadImage(url, new double[]{1,1});
            lowTierGod = new EnemyPlugin();
            lowTierGod.create();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void create() {
        Assertions.assertNotEquals(positions,lowTierGod.getPosition());
        Assertions.assertEquals(10,lowTierGod.getHealth());
        //Assertions.assertTrue(ImageLoader.Comparator(lowTierGodImage.getImage(),lowTierGod.getSprite().getImage()));
    }

    @Test
    void delete() {
        Assertions.assertEquals(0,lowTierGod.delete().getHealth());
    }
}