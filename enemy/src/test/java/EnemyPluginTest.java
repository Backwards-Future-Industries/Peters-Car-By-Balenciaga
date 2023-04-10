import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EnemyPluginTest {
    private EnemyPlugin lowTierGod;
    private double[] positions;
    private BufferedImage lowTierGodImage;

    private boolean ImageComparator(BufferedImage a, BufferedImage b){
        if (a.getWidth() == b.getWidth() && a.getHeight() == b.getHeight()){
            for (int i = 0; i< a.getWidth()* a.getHeight(); i++){
                int row = i / a.getWidth();
                int column = i % a.getHeight();
                if (a.getRGB(row,column)!=b.getRGB(row,column))return false;
            }
        }else {
            return false;
        }
        return true;
    }
    @BeforeEach
    void setUp() {
        positions = new double[]{10, 10};
        try {
            lowTierGodImage = ImageIO.read(EnemyPluginTest.class.getClassLoader().getResource("images/ltg.png"));
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
        Assertions.assertTrue(ImageComparator(lowTierGodImage,lowTierGod.getSprite()));
    }

    @Test
    void delete() {
        Assertions.assertEquals(0,lowTierGod.delete().getHealth());
    }
}