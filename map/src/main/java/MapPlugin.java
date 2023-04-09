
public class MapPlugin {
    private int width = 10;
    private int height = 10;
    private Tile[][] tiles;


    public MapPlugin(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];

        // Fills the map with tiles
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile(x,y);
            }
        }
    }


}
