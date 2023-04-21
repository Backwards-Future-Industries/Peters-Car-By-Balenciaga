package weapons;

import abstractClasses.Entity;
import abstractClasses.Weapon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Grill extends Weapon{


    public Grill(int health, URL sprite) {
        super(health, sprite);
    }

    @Override
    public Entity shoot() throws IOException {
        return null;
    }


    /*public void shoot() throws IOException {
        Bullet bullet = new Bullet();
        bullet.create();
    }*/
}
