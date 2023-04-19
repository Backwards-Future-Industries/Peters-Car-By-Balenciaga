package player;

import interfaces.IDrawable;
import interfaces.IMovement;
import interfaces.IPlugin;
import abstractClasses.Entity;
import interfaces.IProcessing;
import utilities.Inputs;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class PlayerPlugin extends Entity implements IPlugin, IDrawable, IProcessing, IMovement {

    private Entity player;
    private static URL sprite = PlayerPlugin.class.getClassLoader().getResource("images/blueCar.png");

    public PlayerPlugin() throws IOException {
        super(5, sprite);
        setPosition(new int[]{10,10});
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
        int[] desRect = {4,2,5,6};
        g.drawImage(getSprite().getTransformedImage(),posistion[0],posistion[1],panel);
        //g.drawImage(getSprite().getTransformedImage(),getSprite().getTransformOp(),posistion[0],posistion[1]);
        getSprite().rotate(0.001);

    }

    @Override
    public void process(ArrayList<Inputs> inputs) {
        setPosition(defaultMove(inputs, getPosition()));

    }
}
