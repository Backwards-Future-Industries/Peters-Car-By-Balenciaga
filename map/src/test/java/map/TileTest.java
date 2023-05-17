package map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;

class TileTest {

    TileType tileType;
    Tile tile;
    Color expectedColor;

    @Test
    public void testTileGrass() {
        // Checks the color of the bitmap underneath the visual tiles
        tileType = TileType.GRASS;
        tileType.getColor();
        tile = new Tile(tileType);
        expectedColor = Color.black; // Assuming you want the color from the bitmap
        Assertions.assertEquals(expectedColor, tileType.getColor());
        System.out.println(tileType.getColor());
    }

    @Test
    public void testTileEarth() {
        // Checks the color of the bitmap underneath the visual tiles
        tileType = TileType.EARTH;
        tileType.getColor();
        tile = new Tile(tileType);
        expectedColor = Color.white; // Assuming you want the color from the bitmap
        Assertions.assertEquals(expectedColor, tileType.getColor());
        System.out.println(tileType.getColor());
    }
    @Test
    public void testTileObstacle() {
        // Checks the color of the bitmap underneath the visual tiles
        tileType = TileType.OBSTACLE;
        tileType.getColor();
        tile = new Tile(tileType);
        expectedColor = Color.red; // Assuming you want the color from the bitmap
        Assertions.assertEquals(expectedColor, tileType.getColor());
        System.out.println(tileType.getColor());
    }

}