package abstractClasses;

import java.awt.image.BufferedImage;

public abstract class Weapon extends Entity {
    public Weapon(int health, BufferedImage sprite) {
        super(health, sprite);
    }
    public abstract Entity shot();
}
