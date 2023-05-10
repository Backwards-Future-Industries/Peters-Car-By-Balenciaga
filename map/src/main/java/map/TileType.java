package map;

import java.awt.*;

public enum TileType {
    GRASS(Color.white),
    EARTH(Color.black),
    OBSTACLE(Color.red),
    BLANK(Color.GRAY);





    private Color color;

    public Color getColor(){
        return this.color;
    }

    private TileType(Color color){
        this.color = color;
    }

}
