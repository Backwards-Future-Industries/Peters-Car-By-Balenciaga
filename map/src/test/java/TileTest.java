import map.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.TileType;

import java.awt.*;
import java.util.LinkedList;

public class TileTest {

    private LinkedList<Tile> tiles;
    private TileType tileType;
    private Color expectedColor;

    @BeforeEach
    public void setUp() {
        //Arrange
        tiles = new LinkedList<>();

        //Act
        for (TileType tileType : TileType.values()) {
            tiles.add(new Tile(tileType));
        }
    }

    @Test
    public void testConstructor() {
        //Assert
        Assertions.assertNotNull(tiles);
        Assertions.assertTrue(tiles.size() == TileType.values().length);
    }

    // Tests if the bitmaps color correlates to the visual tiles
    @Test
    public void TileGrass() {
        tileType = TileType.GRASS;
        expectedColor = Color.GREEN;
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }

    @Test
    public void TileEarth() {
        tileType = TileType.EARTH;
        expectedColor = Color.WHITE;
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }

    @Test
    public void TileObstacle() {
        tileType = TileType.OBSTACLE;
        expectedColor = Color.RED;
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }

    @Test
    public void TileRoad() {
        tileType = TileType.ROAD;
        expectedColor = Color.GRAY;
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }

    @Test
    public void TileRoadLineUp() {
        tileType = TileType.ROADLINEUP;
        expectedColor = Color.BLUE;
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }

    @Test
    public void TileRoadLineSide() {
        tileType = TileType.ROADLINESIDE;
        expectedColor = Color.MAGENTA;
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }

    @Test
    public void TileSTLeft() {

        tileType = TileType.STLEFT;
        expectedColor = Color.PINK;
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }

    @Test
    public void TileSTRight() {
        tileType = TileType.STRIGHT;
        expectedColor = Color.YELLOW;
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }
}