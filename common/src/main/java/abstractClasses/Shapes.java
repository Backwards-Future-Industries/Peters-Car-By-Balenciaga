package abstractClasses;

import interfaces.IDrawable;

import java.awt.*;
import java.awt.Graphics;

public abstract class Shapes implements IDrawable {

    private int[] position;
    private int x;
    private int y;
    private int width = x*x;
    private int height = y*y;

    Rectangle[] rectangleArray;



    private Graphics graphics;


    public Shapes (int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }





    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[] getPosition() {
        return position;
    }




}
