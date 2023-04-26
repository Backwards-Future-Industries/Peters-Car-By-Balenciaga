package map;

public class Tile {
    private int x;
    private int y;
    private String type;



    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        type = randomTileType();
    }

    // getter for the tile's type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String randomTileType() {
        String[] types = {"Road", "Grass", "Mountain"};
        int randomIndex = (int) (Math.random() * types.length);
        return types[randomIndex];
    }


}
