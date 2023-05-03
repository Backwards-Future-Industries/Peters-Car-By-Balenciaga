package weapon;

import abstractClasses.Entity;
import interfaces.*;
import utilities.Inputs;
import utilities.SPIlocator;
import utilities.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Bullet extends Entity implements IDrawable, IPlugin, IProcessing{

    private final static int health = 1;
    private int[] position;
    private Vector2D direction;
    private int bulletSpeed = 3;
    private int bulletAcceleration = 1;
    private static URL sprite = Bullet.class.getResource("images/bullet.png");

    public Bullet(int[] position, Vector2D direction){
        super(health, sprite, new double[]{0.01, 0.01});
        this.position = position;
        this.direction = direction;
        setPosition(position);
        setDirection(direction);
        setMaxSpeed(bulletSpeed);
        setAcceleration(bulletAcceleration);
    }

    @Override
    public Entity create(IGameEngine gm) {
        Entity newBullet;
        newBullet = new Bullet(position, direction);
        return newBullet;
    }

    @Override
    public Entity delete(IGameEngine gameEngine) {
        return null;
    }

    @Override
    public void draw(Graphics2D g, JPanel panel) {
        int[] position = getPosition();
        g.drawImage(getSprite().getImage(), position[0], position[1], panel);
    }

    @Override
    public void process(ArrayList<Inputs> inputs, IGameEngine gameEngine) {
        for (IMovement iMovement : getPlugin()){
            setPosition(iMovement.defaultMove(new ArrayList<Inputs>(Arrays.asList(Inputs.KEY_W)), this));
        }
    }
    private Collection<IMovement> getPlugin(){
        return SPIlocator.locateAll(IMovement.class);
    }
}
