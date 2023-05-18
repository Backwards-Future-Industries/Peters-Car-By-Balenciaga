package map;

import abstractClasses.Entity;
import utilities.TileType;
import utilities.Type;

public class Tile extends Entity {

    public Tile(TileType tileType) {
        if (tileType == tileType.EARTH){
            setSprite(Tile.class.getResource("/mapImages/earth2.png"),new double[]{1,1});
        }
        if (tileType == tileType.GRASS){
            setSprite(Tile.class.getResource("/mapImages/grass2.png"),new double[]{1,1});
        }
        if (tileType == tileType.OBSTACLE){
            setSprite(Tile.class.getResource("/mapImages/obstacle2.png"),new double[]{1,1},true);
            setType(Type.OBSTACLE);
        }
        if (tileType == tileType.ROAD){
            setSprite(Tile.class.getResource("/mapImages/road2.png"),new double[]{1,1});
        }
        if (tileType == tileType.ROADLINEUP) {
            setSprite(Tile.class.getResource("/mapImages/roadLine.png"), new double[]{1,1});
        }
        if (tileType == tileType.ROADLINESIDE) {
            setSprite(Tile.class.getResource("/mapImages/roadLineSide.png"), new double[]{1,1});
        }
        if (tileType == tileType.STLEFT) {
            setSprite(Tile.class.getResource("/mapImages/STleft.png"), new double[]{1,1});
        }
        if (tileType == tileType.STRIGHT) {
            setSprite(Tile.class.getResource("/mapImages/STright.png"), new double[]{1,1});
        }



    }


}
