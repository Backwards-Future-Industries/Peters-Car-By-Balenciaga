package map;

import abstractClasses.Entity;

public class Tile extends Entity {

    public Tile(TileType tileType) {
        if (tileType == tileType.GRASS){
            setSprite(Tile.class.getResource("/mapImages/grass.png"),new double[]{1,1},true);
        }
        if (tileType == tileType.EARTH){
            setSprite(Tile.class.getResource("/mapImages/earth.png"),new double[]{1,1},true);
        }
        if (tileType == tileType.OBSTACLE){
            setSprite(Tile.class.getResource("/mapImages/obstacle.png"),new double[]{1,1},true);
        }
        if (tileType == tileType.ROAD){
            setSprite(Tile.class.getResource("/mapImages/road.png"),new double[]{1,1},true);
        }

    }

    @Override
    public void onCollision(Entity collidingEntity) {

    }

}
