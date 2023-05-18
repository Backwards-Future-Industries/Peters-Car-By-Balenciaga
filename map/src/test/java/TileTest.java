
import map.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.TileType;

import java.awt.*;

class TileTest {

    TileType tileType;
    Tile tile;
    Color expectedColor;

    @BeforeEach
    public void setup(){

    }

    @Test
    public void testTileGrass() {
        // Checks the color of the bitmap underneath the visual tiles
        tileType = TileType.GRASS;
        tileType.getColor();
        tile = new Tile(tileType);
        expectedColor = Color.GREEN; // Assuming you want the color from the bitmap
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }

    @Test
    public void testTileEarth() {
        // Checks the color of the bitmap underneath the visual tiles
        tileType = TileType.EARTH;
        tileType.getColor();
        tile = new Tile(tileType);
        expectedColor = Color.WHITE; // Assuming you want the color from the bitmap
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }
    @Test
    public void testTileObstacle() {
        // Checks the color of the bitmap underneath the visual tiles
        tileType = TileType.OBSTACLE;
        tileType.getColor();
        tile = new Tile(tileType);
        expectedColor = Color.RED; // Assuming you want the color from the bitmap
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }
    @Test
    public void testTileRoad() {
        // Checks the color of the bitmap underneath the visual tiles
        tileType = TileType.ROAD;
        tileType.getColor();
        tile = new Tile(tileType);
        expectedColor = Color.GRAY; // Assuming you want the color from the bitmap
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }
}