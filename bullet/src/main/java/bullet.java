import abstractClasses.Entity;
import abstractClasses.Weapon;

import java.awt.image.BufferedImage;

public class bullet extends Weapon {

    public bullet(int health, BufferedImage sprite) {
        super(health, sprite);
    }

    @Override
    public Entity shot() {
        return null;
    }
}
