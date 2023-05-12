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


    public PlayerPlugin() {
    }


    @Override
    public Entity create(GameData gamedata) {
        this.newPlayer = new Player();
        this.newPlayer.setHealth(5);
        this.newPlayer.setSprite(sprite, new double[]{0.5, 0.5});
        this.newPlayer.setAcceleration(1);
        this.newPlayer.setMaxSpeed(10);
        this.newPlayer.setTypes(Types.PLAYER);
        this.newPlayer.setPosition(new int[]{1,1});


        return this.newPlayer;
    }


    @Override
    public Entity delete(GameData gameData) {
        return null;
    }

    @Override
    public void draw(Graphics2D g, JPanel panel, GameData gameData) {
        for (Entity player : gameData.getNewEntities()) {
            if (player.getTypes() == Types.PLAYER) {
                int[] position = player.getPosition();

                AffineTransform transform = player.getSprite().getTransform();
                g.setTransform(transform);
                g.drawImage(player.getSprite().getImage(), position[0], position[1], panel);
            }
        }
    }
}
