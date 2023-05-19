package map;

import abstractClasses.Entity;
import utilities.TileType;
import utilities.Type;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Tile extends Entity{

    public Tile(TileType tileType) {
        switch (tileType){
            case EARTH ->
                    setSprite(Tile.class.getResource("/mapImages/earth2.png"),new double[]{1,1});
            case GRASS ->
                    setSprite(Tile.class.getResource("/mapImages/grass2.png"),new double[]{1,1});
            case OBSTACLE -> {
                setSprite(Tile.class.getResource("/mapImages/obstacle2.png"),new double[]{1,1});
                setType(Type.OBSTACLE);
            }
            case ROAD ->
                    setSprite(Tile.class.getResource("/mapImages/road2.png"),new double[]{1,1});
            case ROADLINEUP ->
                    setSprite(Tile.class.getResource("/mapImages/roadLine.png"), new double[]{1,1});
            case ROADLINESIDE ->
                    setSprite(Tile.class.getResource("/mapImages/roadLineSide.png"), new double[]{1,1});
            case STLEFT ->
                    setSprite(Tile.class.getResource("/mapImages/STleft.png"), new double[]{1,1});
            case STRIGHT ->
                    setSprite(Tile.class.getResource("/mapImages/STright.png"), new double[]{1,1});

        }
    }

    @Override
    public String toString(){
        return this.getPosition()[0]+";"+this.getPosition()[1];
    }
}
