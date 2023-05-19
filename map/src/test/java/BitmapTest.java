import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import abstractClasses.CommonMap;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import map.Bitmap;
import utilities.TileType;

public class BitmapTest {

    private Bitmap bitmap;
    
    @BeforeEach
    public void setUp() {
        bitmap = new Bitmap();

    }

    @Test
    public void getMap() {
        TileType[][] map = bitmap.getMap();
        // Perform assertions on the map
        Assertions.assertNotNull(map);
    }

    @Test
    public void setBitmap() {
        BufferedImage testBitmap = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
        bitmap.setBitmap(testBitmap);
        Assertions.assertEquals(testBitmap, bitmap.getBitmap());
    }

    @Test
    public void getAiMap() {
        int[][] aiMap = bitmap.getAiMap();
        // Perform assertions on the aiMap
        Assertions.assertNotNull(aiMap);
    }


    @Test
    public void testConstructor() {
        URL url = Bitmap.class.getResource("/bitmaps/bitMap8.0.png");
        Bitmap bitmap = new Bitmap();
        Assertions.assertNotNull(bitmap.getMap());
        Assertions.assertNotNull(bitmap.getAiMap());
        Assertions.assertTrue(bitmap.getMap().length > 0);
        Assertions.assertTrue(bitmap.getAiMap().length > 0);
        // Additional assertions based on your specific requirements
    }


}