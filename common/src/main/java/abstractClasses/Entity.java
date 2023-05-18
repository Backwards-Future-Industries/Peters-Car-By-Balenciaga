package abstractClasses;

import utilities.Type;

import utilities.Vector2D;

import java.net.URL;

public abstract class Entity extends Progenitor{
    private int health;
    private Type type;
    private double acceleration;
    private double maxSpeed;
    // Instead of radians, I have made a Shape-Array that contains shapes. The shapes in the array will be the mapImages
    private Vector2D direction;
    
    public Entity(){
        this.health = 1;
        this.type = Type.UNDEFINED;
        this.acceleration = 1.;
        this.maxSpeed = 1.;
        this.direction = new Vector2D(0.,0.);;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
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

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
