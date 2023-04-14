import abstractClasses.Entity;
import abstractClasses.Weapon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Grill extends Weapon{

    private static URL bullet = Grill.class.getClassLoader().getResource("images/bullet.png");

    public Grill(int health, BufferedImage sprite) {
        super(health, sprite);
    }

    @Override
    public Entity shot() throws IOException {
        return new Bullet(1, ImageIO.read(bullet));
    }
}
