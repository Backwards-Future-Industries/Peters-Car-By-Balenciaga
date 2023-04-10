import abstractClasses.Entity;
import abstractClasses.Weapon;

import java.awt.image.BufferedImage;

public class Grill extends Weapon{
    public Grill(int health, BufferedImage sprite) {
        super(health, sprite);
    }

    @Override
    public Entity shot() {
        return new Bullet(1, null);
    }
}
