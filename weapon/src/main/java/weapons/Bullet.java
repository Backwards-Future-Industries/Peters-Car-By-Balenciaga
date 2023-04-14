package weapons;

import abstractClasses.Entity;
import interfaces.*;
import java.awt.image.BufferedImage;

public class Bullet extends Entity implements IDrawable, IMovement, IPlugin{

    public Bullet(int health, BufferedImage sprite) {
        super(health, sprite);
    }

    @Override
    public void draw() {

    }


    @Override
    public boolean create() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }
}
