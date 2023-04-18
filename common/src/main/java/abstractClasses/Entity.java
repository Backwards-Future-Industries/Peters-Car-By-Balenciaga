package abstractClasses;

import utilities.image.Image;
import utilities.image.ImageLoader;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.net.URL;

public abstract class Entity {
    private int health;

    private Image sprite;
    private int[] position;
    private double radius;


    public Entity(int health, URL sprite){
        this.health = health;
        this.sprite = ImageLoader.loadImage(sprite);
        radius = 20; //placeholder default value
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public synchronized Image getSprite() {
        return sprite;
    }

    public void setSprite(URL sprite) {
        this.sprite = ImageLoader.loadImage(sprite);
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int[] getPosition() {
        return position;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
