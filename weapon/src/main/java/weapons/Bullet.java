package weapons;

import abstractClasses.Entity;
import interfaces.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Bullet extends Entity implements IDrawable, IMovement, IPlugin{

    private final static int health = 1;
    private static URL sprite = Bullet.class.getClassLoader().getResource("images/bullet.png");


    public Bullet() throws IOException {
        super(health, ImageIO.read(sprite));
    }

    @Override
    public void draw(Graphics g) {

    }


    @Override
    public Entity create() {
        Entity newBullet;
        try {
            newBullet = new Bullet();
        } catch (IOException exception) {
            throw new RuntimeException();
        }
        return newBullet;
    }

    @Override
    public Entity delete() {
        return null;
    }
}
