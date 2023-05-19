package map;

import abstractClasses.Entity;
import utilities.TileType;
import utilities.Type;
import java.util.Arrays;
import java.util.List;

public class Tile extends Entity {
    public Tile(TileType tileType) {
        List<TileType> listOfTileTypes = Arrays.asList(TileType.values());
        for (TileType tile : listOfTileTypes) {
            if (tileType == tile) {
                if (tileType == TileType.OBSTACLE) {
                    setType(Type.OBSTACLE);
                }
                setSprite(Tile.class.getResource("/mapImages/" + tile.toString() + ".png"), new double[]{1, 1});
            }
        }
    }

}
