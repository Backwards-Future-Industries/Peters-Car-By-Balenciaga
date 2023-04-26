package abstractClasses;

import interfaces.IDrawable;
import utilities.image.Image;
import utilities.image.ImageLoader;

import java.awt.*;
import java.awt.Graphics;
import java.net.URL;

public abstract class Shapes extends Entity implements IDrawable {



    private int width;
    private int height;

    Rectangle[] rectangleArray;

    int health;
    private Image texture;

    double[] scale;

    private Graphics graphics;


    public Shapes(int health, URL texture, double[] scale) {
        super(health,texture,scale);
        this.texture = ImageLoader.loadImage(texture,scale);
        this.scale = scale;
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






}
