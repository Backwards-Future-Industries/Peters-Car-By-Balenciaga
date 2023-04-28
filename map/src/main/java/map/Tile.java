package map;

import abstractClasses.Entity;

import java.net.URL;

public class Tile extends Entity {

    public Tile(eTile eTile) {
        if (eTile == eTile.EARTH){
            setSprite(Tile.class.getResource("/mapImages/earth.png"),new double[]{1,1});
        }
        if (eTile == eTile.GRASS){
            setSprite(Tile.class.getResource("/mapImages/grass.png"),new double[]{1,1});
        }
    }
}
