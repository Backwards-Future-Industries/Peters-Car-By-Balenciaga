import abstractClasses.CommonMap;
import map.Bitmap;
import map.Map;
import map.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.GameData;
import utilities.TileType;

import java.awt.image.BufferedImage;

public class MapTest {

    private Map map;
    private GameData gameData;
    private Bitmap bitmap;

    private Tile grass;
    private Tile earth;
    private Tile obstacle;
    private Tile road;
    private Tile roadLineUp;
    private Tile roadLineSide;
    private Tile stLeft;
    private Tile stRight;
    private BufferedImage bufferedImage;

    private Tile expectedTile;
    @BeforeEach
    public void setUp() {
        GameData gameData = new GameData();
        map = new Map(gameData);
    }
    @Test
    public void testCreate() {
        GameData gameData = new GameData();
        CommonMap mapResult = map.create(gameData);
        Assertions.assertNotNull(mapResult);
    }
    @Test
    public void Map() {
        this.bitmap = new Bitmap();
        this.grass = new Tile(TileType.GRASS);
        this.earth = new Tile(TileType.EARTH);
        this.obstacle = new Tile(TileType.OBSTACLE);
        this.road = new Tile(TileType.ROAD);
        this.roadLineUp = new Tile(TileType.ROADLINEUP);
        this.roadLineSide = new Tile(TileType.ROADLINESIDE);
        this.stLeft = new Tile(TileType.STLEFT);
        this.stRight = new Tile(TileType.STRIGHT);
        Assertions.assertNotNull(bitmap.getMap());
    }
    


}