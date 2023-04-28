package player;

import interfaces.*;
import abstractClasses.Entity;
import utilities.Inputs;
import utilities.SPIlocator;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;


public class PlayerPlugin extends Entity implements IPlugin, IDrawable, IProcessing {

    private static final URL sprite = PlayerPlugin.class.getResource("/playerImages/blueCar.png");

    public PlayerPlugin() throws IOException {
        super(5, sprite, new double[]{0.5,0.5},1,10);
        setPosition(new int[]{700,500});
        setRadians(0);
    }


    @Override
    public Entity create(IGameEngine gameEngine) {
        Entity newPlayer;
        try {
            newPlayer = new PlayerPlugin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newPlayer;
    }


    @Override
    public Entity delete(IGameEngine gameEngine) {
        return null;
    }

    @Override
    public void draw(Graphics2D g, JPanel panel) {
        int[] position = getPosition();

        AffineTransform transform = getSprite().getTransform();
        g.setTransform(transform);
        g.drawImage(getSprite().getImage(),position[0],position[1],panel);
    }

    @Override
    public void process(ArrayList<Inputs> inputs, IGameEngine gameEngine) {
        for (IMovement iMovement : getPlugin()){
            setPosition(iMovement.defaultMove(inputs,this));
        }
        this.getSprite().freshRotate(this.getRadians(),this.getPosition());
    }

    private Collection<IMovement> getPlugin(){
        System.out.println(SPIlocator.locateAll(IMovement.class));
        return SPIlocator.locateAll(IMovement.class);
    }
}
