package abstractClasses;

import utilities.Vector2D;

import java.awt.image.BufferedImage;

public abstract class Entity {
    private int health;

    private BufferedImage sprite;
    private int[] position;
    private double radius;
    private double acceleration;
    private double maxSpeed;
    private double radians = 0;
    private Vector2D direction;


    public Entity(int health, BufferedImage sprite){
        this.health = health;
        this.sprite = sprite;
        radius = 20; //placeholder default value
    }

    public Entity(int health, BufferedImage sprite, int acceleration, int maxSpeed){
        this.health = health;
        this.sprite = sprite;
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

    public synchronized BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
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
