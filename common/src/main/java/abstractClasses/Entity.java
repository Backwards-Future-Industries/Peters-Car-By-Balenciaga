package abstractClasses;

import utilities.image.Image;
import utilities.image.ImageLoader;

import java.awt.geom.AffineTransform;
import utilities.Vector2D;

import java.awt.image.BufferedImage;
import java.net.URL;

public abstract class Entity {
    private int health;

    private Image sprite;
    private int[] position;
    private double[] scale;
    private double radius;
    private double acceleration;
    private double maxSpeed;
    private double radians = 0;
    private Vector2D direction;

    public Entity(int health, URL sprite, double[] scale){
        this.health = health;
        this.scale = scale;
        this.sprite = ImageLoader.loadImage(sprite,scale);
        radius = 20; //placeholder default value
    }
    
    public Entity(int health, URL sprite, int acceleration, int maxSpeed){
        this(health,sprite,new double[]{1,1});
        this.acceleration = acceleration;
        this.maxSpeed   = maxSpeed;
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
        this.sprite = ImageLoader.loadImage(sprite, scale);
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

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getRadians() {
        return radians;
    }

    public void setRadians(double radians) {
        this.radians = radians;
    }

    public Vector2D getDirection() {
        return direction;
    }

    public void setDirection(Vector2D direction) {
        this.direction = direction;
    }
}
