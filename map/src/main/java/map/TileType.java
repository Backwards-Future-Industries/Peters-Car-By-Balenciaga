package map;

import java.awt.*;

public enum TileType {
    GRASS(Color.black),
    EARTH(Color.yellow),
    OBSTACLE(Color.red),
    ROAD(Color.gray),
    BLANK(Color.cyan);

    private Color color;

    public Color getColor(){
        return this.color;
    }

    private TileType(Color color){
        this.color = color;
    }

}
