package abstractClasses;

import java.awt.image.BufferedImage;

public abstract class Entity {
    private int health;
    private BufferedImage sprite;
    private double positionX;
    private  double positionY;


    public Entity(int health, BufferedImage sprite){
        this.health = health;
        this.sprite = sprite;
    }

    int getHealth() {
        return health;
    }
    void setHealth(int health) {
        this.health = health;
    }

    public synchronized BufferedImage getSprite() {
        return sprite;
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public double[] getPosition() {
        return new double[]{this.positionX,this.positionY};
    }
}
