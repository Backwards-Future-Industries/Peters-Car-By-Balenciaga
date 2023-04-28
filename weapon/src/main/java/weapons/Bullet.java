package weapons;

import abstractClasses.Entity;
import interfaces.*;
import utilities.Inputs;
import utilities.Vector2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class Bullet extends Entity implements IDrawable, IMovement, IPlugin, IProcessing{

    private final static int health = 1;
    private static URL sprite = Bullet.class.getClassLoader().getResource("images/bullet.png");

    public Bullet() throws IOException {
        super(health, sprite, new double[]{0.01, 0.01});
        setPosition(new int[] {20, 20});
        setDirection(new Vector2D(0, 0));
        setRadians(0.5);
        setMaxSpeed(2);
        setAcceleration(1);
    }


    @Override
    public Entity create(GameEngine gm) {
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

    @Override
    public void draw(Graphics2D g, JPanel panel) {
        int[] position = getPosition();
        g.drawImage(getSprite().getImage(), position[0], position[1], panel);
    }

    @Override
    public void process(ArrayList<Inputs> inputs) {
        setPosition(defaultMove(new ArrayList<Inputs>(Arrays.asList(Inputs.KEY_W)), this));
    }
}
