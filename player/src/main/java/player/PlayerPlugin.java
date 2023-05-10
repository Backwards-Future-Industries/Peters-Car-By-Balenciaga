package player;

import interfaces.*;
import abstractClasses.Entity;
import utilities.GameData;
import utilities.Types;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;



public class PlayerPlugin implements IPlugin, IDrawable {

    private Entity newPlayer;

    private static final URL sprite = PlayerPlugin.class.getResource("/playerImages/blueCar.png");

    public PlayerPlugin(){
    }


    @Override
    public Entity create(GameData gamedata) {
        this.newPlayer = new Player();
        this.newPlayer.setHealth(5);
        this.newPlayer.setSprite(sprite,new double[]{0.5,0.5});
        this.newPlayer.setAcceleration(1);
        this.newPlayer.setMaxSpeed(10);
        this.newPlayer.setTypes(Types.PLAYER);


        return this.newPlayer;
    }


    @Override
    public Entity delete(IGameEngine gameEngine) {
        return null;
    }

    @Override
    public void draw(Graphics2D g, JPanel panel) {
        int[] position = newPlayer.getPosition();

        AffineTransform transform = newPlayer.getSprite().getTransform();
        g.setTransform(transform);
        g.drawImage(newPlayer.getSprite().getImage(),position[0],position[1],panel);
    }
}
