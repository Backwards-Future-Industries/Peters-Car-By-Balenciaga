package abstractClasses;

import java.io.IOException;
import java.net.URL;

public abstract class Weapon extends Entity {
    public Weapon(int health, URL sprite) {
        super(health, sprite);
    }
    public abstract void shoot() throws IOException;
}
