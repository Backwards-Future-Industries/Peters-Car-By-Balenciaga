package map;

import map.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.TileType;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    private Map map;

    private Tile grass;
    private Tile earth;
    private Tile obstacle;

    @BeforeEach
    void setMap() {
        this.map = new Map();
        this.grass = new Tile(TileType.GRASS);
        this.earth = new Tile(TileType.EARTH);
        this.obstacle = new Tile(TileType.OBSTACLE);
    }


    @Test
    void create() {
    }

    @Test
    void delete() {
    }

    @Test
    void draw() {
    }

    @Test
    void getLayer() {
    }

    @Test
    void testToString() {
    }
}