package weapons;

import abstractClasses.Entity;
import abstractClasses.Weapon;
import interfaces.IDrawable;
import interfaces.IPlugin;
import interfaces.IProcessing;
import utilities.Inputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Grill extends Weapon implements IDrawable, IProcessing, IPlugin{

    private static URL sprite = Entity.class.getClassLoader().getResource("images/placeholder.png");

    public Grill(int health) {
        super(health, sprite);
        setPosition(new int[]{50, 50});
    }

    @Override
    public void draw(Graphics2D g, JPanel panel) {
        int [] position = getPosition();
        g.drawImage(getSprite().getImage(), position[0], position[1], panel);
    }

    @Override
    public Entity create() {
        Entity newGrill;
        newGrill = new Grill(1);
        return newGrill;
    }

    @Override
    public Entity delete() {
        return null;
    }

    @Override
    public void process(ArrayList<Inputs> inputs) {
        if(inputs.contains(Inputs.KEY_SPACE)) {
            try {
                this.shoot();
                System.out.println(inputs);
                inputs.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void shoot() throws IOException {
        Bullet bullet = new Bullet();
        bullet.create();
    }
}
