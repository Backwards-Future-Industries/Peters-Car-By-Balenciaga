package abstractClasses;

import utilities.Shapes;
import utilities.Type;
import utilities.image.Image;
import utilities.image.ImageLoader;

import utilities.Vector2D;

import java.awt.image.BufferedImage;
import java.net.URL;

public abstract class Entity extends Progenitor{
    private int health;
    private Type type;
    private double radius;
    private double acceleration;
    private double maxSpeed;
    // Instead of radians, I have made a Shape-Array that contains shapes. The shapes in the array will be the mapImages
    private Vector2D direction;
    private Shapes shape;
    
    public Entity(){
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
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

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Vector2D getDirection() {
        return direction;
    }

    public void setDirection(Vector2D direction) {
        this.direction = direction;
    }

    public void setShape(Shapes shape) {
        this.shape = shape;
    }

    public void setSprite(URL sprite, double[] scale, boolean updateShape) {
        super.setSprite(sprite,scale);
        if (updateShape){
            setShape(new Shapes(getSprite().getImage().getWidth(),getSprite().getImage().getHeight(),getPosition(), getType()));
        }
    }

    public Shapes getShape() {
        return shape;
    }
    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public void setPosition(int[] position) {
        super.setPosition(position);
        this.shape.setPosition(position);
    }
}
