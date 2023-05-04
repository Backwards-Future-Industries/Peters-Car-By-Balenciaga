package utilities;

import abstractClasses.Entity;

public class Shapes {

    int[] position;
    int height;
    int width;

    public Shapes(int width, int height) {
        this.width = width;
        this.height = height;

        setPosition(new int[]{1,1});

    }


    public int[] getPosition() {
        return position;
    }


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
