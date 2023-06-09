package abstractClasses;

import utilities.image.Image;
import utilities.image.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public abstract class Progenitor {
    private Image sprite;
    private double[] scale;
    private Point position;
    private double radians;

    public Progenitor() {
        this.position = new Point(0, 0);
        this.scale = new double[]{1, 1};
        this.radians = 0.;
        this.sprite = ImageLoader.loadImage(Entity.class.getResource("/commonImages/placeholder.png"), scale);
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
    }

    public void setScale(double[] scale) {
        this.scale = scale;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public double getRadians() {
        return radians;
    }

    public void setRadians(double radians) {
        this.radians = radians;
    }
}
