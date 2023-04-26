package map;

import abstractClasses.Entity;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

public class Tile extends Entity {

    public Tile(eTile eTile) {
        if (eTile == eTile.EARTH){
            setSprite(Tile.class.getClassLoader().getResource("images/earth.png"),new double[]{1,1});
        }
        if (eTile == eTile.GRASS){
            setSprite(Tile.class.getClassLoader().getResource("images/grass.png"),new double[]{1,1});
        }
    }
}
