package map;

import java.awt.*;

public enum eTile {
    GRASS(Color.white),EARTH(Color.black),BLANK(Color.GRAY);

    private Color color;

    public Color getColor(){
        return this.color;
    }

    private eTile(Color color){
        this.color = color;
    }

}
