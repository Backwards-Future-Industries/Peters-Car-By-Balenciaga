package map;

import abstractClasses.Entity;

public class Tile extends Entity {

    public Tile(TileType tileType) {
        if (tileType == tileType.EARTH){
            setSprite(Tile.class.getResource("/mapImages/earth.png"),new double[]{1,1});
        }
        if (tileType == tileType.GRASS){
            setSprite(Tile.class.getResource("/mapImages/grass.png"),new double[]{1,1});
        }
    }
}
