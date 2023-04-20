package abstractClasses;

import java.awt.image.BufferedImage;
//<<<<<<< HEAD
import java.io.IOException;
//=======
import java.net.URL;
//>>>>>>> main

public abstract class Weapon extends Entity {
    public Weapon(int health, URL sprite) {
        super(health, sprite);
    }
    public abstract Entity shoot() throws IOException;
}
