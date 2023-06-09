package utilities;

import java.awt.*;

public enum TileType {
    GRASS(Color.GREEN),
    EARTH(Color.WHITE),
    OBSTACLE(Color.RED),
    ROAD(Color.GRAY),
    ROADLINEUP(Color.BLUE),
    ROADLINESIDE(Color.MAGENTA),
    STLEFT(Color.PINK),
    STRIGHT(Color.YELLOW),
    PARKING(Color.ORANGE),
    BLANK(Color.CYAN);

    private Color color;

    public Color getColor() {
        return this.color;
    }

    TileType(Color color) {
        this.color = color;
    }

}
