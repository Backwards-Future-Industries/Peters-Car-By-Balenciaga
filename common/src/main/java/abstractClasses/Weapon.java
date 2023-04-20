package abstractClasses;

import java.awt.image.BufferedImage;
import java.net.URL;

public abstract class Weapon extends Entity {
    public Weapon(int health, URL sprite) {
        super(health, sprite);
    }
    public abstract Entity shot();
}
