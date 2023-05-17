package utilities;

import java.awt.*;

public enum TileType {
    GRASS(Color.GREEN),
    EARTH(Color.WHITE),
    OBSTACLE(Color.RED),
    ROAD(Color.GRAY),
    BLANK(Color.CYAN);

    private Color color;

    public Color getColor(){
        return this.color;
    }

    private TileType(Color color){
        this.color = color;
    }

}
