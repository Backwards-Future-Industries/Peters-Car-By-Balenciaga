package player;

import interfaces.IDrawable;
import interfaces.IMovement;
import interfaces.IPlugin;
import abstractClasses.Entity;
import interfaces.IProcessing;
import utilities.Inputs;
import utilities.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class PlayerPlugin extends Entity implements IPlugin, IDrawable, IProcessing, IMovement {

    private Entity player;

    private static URL sprite = PlayerPlugin.class.getClassLoader().getResource("images/blueCar.png");

    public PlayerPlugin() throws IOException {
        super(5, sprite, new double[]{0.5,0.5},1,5);
        setPosition(new int[]{10,10});
        setRadians(Math.PI*((double) 1/2));
        setDirection(new Vector2D(0,0));
    }

    @Override
    public Entity create() {
        Entity newPlayer;
        try {
            newPlayer = new PlayerPlugin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newPlayer;
    }


    @Override
    public Entity delete() {
        return null;
    }

    @Override
    public void draw(Graphics2D g, JPanel panel) {
        int[] posistion = getPosition();

        AffineTransform transform = getSprite().getTransform();
        g.setTransform(transform);
        g.drawImage(getSprite().getImage(),posistion[0],posistion[1],panel);

    }

    @Override
    public void process(ArrayList<Inputs> inputs) {
        setPosition(defaultMove(inputs,this));
        this.getSprite().freshRotate(this.getRadians(),this.getPosition());

    }
}
