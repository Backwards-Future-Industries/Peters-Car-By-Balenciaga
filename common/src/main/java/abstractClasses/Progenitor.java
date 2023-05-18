package abstractClasses;

import utilities.Shape;
import utilities.image.Image;
import utilities.image.ImageLoader;

import java.awt.image.BufferedImage;
import java.net.URL;

public abstract class Progenitor {
    private Image sprite;
    private double[] scale;
    private int[] position;
    private double radians;
    private Shape shape;

    public Progenitor(){
        this.position = new int[]{0,0};
        this.scale = new double[]{1,1};
        this.radians = 0.;
        this.sprite = ImageLoader.loadImage(Entity.class.getResource("/commonImages/placeholder.png"),scale);
        this.shape = new Shape(1,1);
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(URL sprite, double[] scale) {
        this.scale = scale;
        this.sprite = ImageLoader.loadImage(sprite, this.scale);
    }
    public void setSprite(BufferedImage sprite, double[] scale) {
        this.scale = scale;
        this.sprite = ImageLoader.loadImage(sprite, this.scale);
        System.out.println();
    }


    public void setScale(double[] scale) {
        this.scale = scale;
    }
    public int[] getPosition() {
        return position;
    }
    public void setPosition(int[] position) {
        this.position = position;
        this.shape.setPosition(position,this.radians);
    }
    public double getRadians() {
        return radians;
    }

    public void setRadians(double radians) {
        this.radians = radians;
        this.shape.rotate(radians);

    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }
}
