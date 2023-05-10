package grill;

import abstractClasses.Entity;
import interfaces.*;
import utilities.Inputs;
import utilities.SPIlocator;
import utilities.Types;
import utilities.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bullet extends Entity implements IDrawable, IPlugin, IProcessing{
    private static final URL sprite = Bullet.class.getResource("images/bullet.png");

    public Bullet(){
        this(new int[]{0,0},new Vector2D(1.0,1.0));

    }

    public Bullet(int[] position, Vector2D direction){
        super(1, sprite, Types.BULLET, new double[]{0.01, 0.01});
        setPosition(position);
        setDirection(direction);
        setMaxSpeed(3);
        setAcceleration(1);
    }

    @Override
    public Entity create(IGameEngine gm) {
        Entity newBullet;
        newBullet = new Bullet(getPosition(), getDirection());
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
            setPosition(iMovement.defaultMove(new ArrayList<>(List.of(Inputs.KEY_W)), this));
        }
    }
    private Collection<IMovement> getPlugin(){
        return SPIlocator.locateAll(IMovement.class);
    }
}
