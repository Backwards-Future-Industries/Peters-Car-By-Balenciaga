package abstractClasses;

import java.awt.image.BufferedImage;

public abstract class Entity {
    private int health;
    private BufferedImage sprite;


    public Entity(int health, BufferedImage sprite){
        this.health = health;
        this.sprite = sprite;
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
}
