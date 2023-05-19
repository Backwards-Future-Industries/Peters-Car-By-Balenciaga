package map;

import abstractClasses.Entity;
import utilities.TileType;
import utilities.Type;

public class Tile extends Entity {
    public Tile(TileType tileType) {
        switch (tileType) {
            case EARTH -> setSprite(Tile.class.getResource("/mapImages/EARTH.png"), new double[]{1, 1});
            case GRASS -> setSprite(Tile.class.getResource("/mapImages/GRASS.png"), new double[]{1, 1});
            case OBSTACLE -> {
                setSprite(Tile.class.getResource("/mapImages/OBSTACLE.png"), new double[]{1, 1});
                setType(Type.OBSTACLE);
            }
            case ROAD -> setSprite(Tile.class.getResource("/mapImages/ROAD.png"), new double[]{1, 1});
            case ROADLINEUP -> setSprite(Tile.class.getResource("/mapImages/ROADLINEUP.png"), new double[]{1, 1});
            case ROADLINESIDE -> setSprite(Tile.class.getResource("/mapImages/ROADLINESIDE.png"), new double[]{1, 1});
            case STLEFT -> setSprite(Tile.class.getResource("/mapImages/STLEFT.png"), new double[]{1, 1});
            case STRIGHT -> setSprite(Tile.class.getResource("/mapImages/STRIGHT.png"), new double[]{1, 1});

        }
    }

}
