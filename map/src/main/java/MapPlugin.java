
public class MapPlugin {
    private int width;
    private int height;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void display() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.println(tiles[x][y].getType() + " ");
            }
            System.out.println();
        }
    }


}
