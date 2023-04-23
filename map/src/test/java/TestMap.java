

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMap {
    private MapPlugin mapPlugin;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testMapWidth() {
        mapPlugin = new MapPlugin(5,5);
        Assertions.assertEquals(5,mapPlugin.getWidth());
    }

    @Test
    public void testMapHeight() {
        mapPlugin = new MapPlugin(5,5);
        Assertions.assertEquals(5,mapPlugin.getHeight());
    }

    @Test
    public void testTileType() {
        Tile tile = new Tile(0,0);
        Assertions.assertNotNull(tile.getType());
    }
}
