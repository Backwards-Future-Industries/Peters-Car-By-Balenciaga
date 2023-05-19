import map.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.TileType;

import java.awt.*;

public class TileTest {

    Tile tile;
    TileType tileType;
    Color expectedColor;

    @BeforeEach
    public void setup(){

    }
    // Tests if the bitmaps color correlates to the visual tiles
    @Test
    public void TileGrass() {
        tileType = TileType.GRASS;
        tileType.getColor();
        tile = new Tile(tileType);
        expectedColor = Color.GREEN;
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }

    @Test
    public void TileEarth() {
        tileType = TileType.EARTH;
        tileType.getColor();
        tile = new Tile(tileType);
        expectedColor = Color.WHITE;
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }
    @Test
    public void TileObstacle() {
        tileType = TileType.OBSTACLE;
        tileType.getColor();
        tile = new Tile(tileType);
        expectedColor = Color.RED;
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }
    @Test
    public void TileRoad() {
        tileType = TileType.ROAD;
        tileType.getColor();
        tile = new Tile(tileType);
        expectedColor = Color.GRAY;
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }
    @Test
    public void TileRoadLineUp() {
        tileType = TileType.ROADLINEUP;
        tileType.getColor();
        tile = new Tile(tileType);
        expectedColor = Color.BLUE;
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }
    @Test
    public void TileRoadLineSide() {

        tileType = TileType.ROADLINESIDE;
        tileType.getColor();
        tile = new Tile(tileType);
        expectedColor = Color.MAGENTA;
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }
    @Test
    public void TilestLeft() {

        tileType = TileType.STLEFT;
        tileType.getColor();
        tile = new Tile(tileType);
        expectedColor = Color.PINK;
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }
    @Test
    public void TilestRight() {
        tileType = TileType.STRIGHT;
        tileType.getColor();
        tile = new Tile(tileType);
        expectedColor = Color.YELLOW;
        Assertions.assertEquals(expectedColor, tileType.getColor());
    }
}