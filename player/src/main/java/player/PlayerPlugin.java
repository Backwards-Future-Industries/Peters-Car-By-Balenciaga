package player;

import interfaces.*;
import abstractClasses.Entity;
import utilities.GameData;
import utilities.Layers;
import utilities.Type;
import utilities.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;



public class PlayerPlugin implements IPlugin, IDrawable {

    private Entity newPlayer;

    private static final URL sprite = PlayerPlugin.class.getResource("/playerImages/petersCar.png");


    public PlayerPlugin() {
    }


    @Override
    public Entity create() {
        this.newPlayer = new Player();
        this.newPlayer.setHealth(5);
        this.newPlayer.setPosition(new int[]{1280,900});
        this.newPlayer.setSprite(sprite, new double[]{0.2, 0.2});
        this.newPlayer.setAcceleration(0.2);
        this.newPlayer.setMaxSpeed(1.5);
        this.newPlayer.setType(Type.PLAYER);

        return this.newPlayer;
    }


    @Override
    public Entity delete(GameData gameData) {
        return null;
    }

    @Override
    public void draw(Graphics2D g, JPanel panel, GameData gameData) {
        for (Entity player : gameData.getEntityList(Type.PLAYER)) {
            if (player.getType() == Type.PLAYER) {

                int[] position = player.getPosition();

                AffineTransform transform = player.getSprite().getTransform();
                g.setTransform(transform);
                g.drawImage(player.getSprite().getImage(), position[0], position[1], panel);
            }
        }
    }

    @Override
    public Layers getLayer() {
        return Layers.MIDDLEGROUND;
    }

    @Override
    public String toString(){
        return Type.PLAYER.toString();
    }
}
