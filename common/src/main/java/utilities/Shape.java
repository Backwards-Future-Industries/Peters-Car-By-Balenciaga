package utilities;

import abstractClasses.Entity;

public class Shape extends Entity {

    int[] position;
    int height;
    int width;

    public Shape () {

    }

    @Override
    public int[] getPosition() {
        return position;
    }

    @Override
    public void setPosition(int[] position) {
        this.position = position;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
