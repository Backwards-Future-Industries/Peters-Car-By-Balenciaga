package abstractClasses;

import java.awt.image.BufferedImage;

public abstract class Entity {
    private int health;

    private BufferedImage sprite;
    private int[] position;
    private double radius;


    public Entity(int health, BufferedImage sprite){
        this.health = health;
        this.sprite = sprite;
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
}
