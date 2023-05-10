package player;

import interfaces.*;
import abstractClasses.Entity;
import utilities.GameData;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;



public class PlayerPlugin extends Entity implements IPlugin, IDrawable {

    private static final URL sprite = PlayerPlugin.class.getResource("/playerImages/blueCar.png");

    public PlayerPlugin() throws IOException {
        super(5, sprite, new double[]{0.5,0.5},1,10);
        setPosition(new int[]{700,500});
        setRadians(0);
    }


    @Override
    public Entity create(GameData gamedata) {
        Entity newPlayer;
        try {
            newPlayer = new PlayerPlugin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gamedata.addDrawables((IDrawable) newPlayer);
        gamedata.addProcesses((IProcessing) newPlayer);
        return newPlayer;
    }

    @Override
    public Entity delete(GameData gameEngine) {
        return null;
    }

    @Override
    public void draw(Graphics2D g, JPanel panel) {
        int[] position = getPosition();

        AffineTransform transform = getSprite().getTransform();
        g.setTransform(transform);
        g.drawImage(getSprite().getImage(),position[0],position[1],panel);
    }
}
