import map.Bitmap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.TileType;

import java.awt.image.BufferedImage;

public class BitmapTest {

    private Bitmap bitmap;
    private TileType[][] map;
    private BufferedImage testBitmap;
    private int[][] aiMap;


    @BeforeEach
    public void setUp() {
        //Arrange
        bitmap = new Bitmap();
        testBitmap = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
    }

    @Test
    public void getMap() {
        //Act
        map = bitmap.getMap();

        //Assert
        Assertions.assertNotNull(map);
    }

    @Test
    public void setBitmap() {
        //Act
        bitmap.setBitmap(testBitmap);

        //Assert
        Assertions.assertEquals(testBitmap, bitmap.getBitmap());
    }

    @Test
    public void getAiMap() {
        //Act
        aiMap = bitmap.getAiMap();

        //Assert
        Assertions.assertNotNull(aiMap);
    }


    @Test
    public void testConstructor() {
        //Assert
        Assertions.assertNotNull(bitmap.getMap());
        Assertions.assertNotNull(bitmap.getAiMap());
        Assertions.assertTrue(bitmap.getMap().length > 0);
        Assertions.assertTrue(bitmap.getAiMap().length > 0);
    }


}