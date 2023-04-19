package player;

import interfaces.IDrawable;
import interfaces.IMovement;
import interfaces.IPlugin;
import abstractClasses.Entity;
import interfaces.IProcessing;
import utilities.Inputs;
import utilities.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class PlayerPlugin extends Entity implements IPlugin, IDrawable, IProcessing, IMovement {

    private Entity player;

    private static URL sprite = PlayerPlugin.class.getClassLoader().getResource("images/blueCar.png");

    public PlayerPlugin() throws IOException {
        super(5, ImageIO.read(sprite),1,5);
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
    public void draw(Graphics g) {
        int[] posistion = getPosition();
        g.fillRect(posistion[0],posistion[1],50,50);

    }

    @Override
    public void process(ArrayList<Inputs> inputs) {
        setPosition(defaultMove(inputs,this));

    }
}
