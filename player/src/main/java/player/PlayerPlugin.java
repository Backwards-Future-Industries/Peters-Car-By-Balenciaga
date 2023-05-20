package player;

import abstractClasses.Entity;
import interfaces.IDrawable;
import interfaces.IPlugin;
import utilities.GameData;
import utilities.Layer;
import utilities.Type;

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
        this.newPlayer.setPosition(new Point(1260, 900));
        this.newPlayer.setSprite(sprite, new double[]{0.2, 0.2});
        this.newPlayer.setAcceleration(0.2);
        this.newPlayer.setMaxSpeed(1.8);
        this.newPlayer.setType(Type.PLAYER);

        return this.newPlayer;
    }


    @Override
    public void delete(GameData gameData, Entity entity) {
        gameData.getEntityList(this.newPlayer.getType()).remove(entity);
    }


    @Override
    public void draw(Graphics2D g, JPanel panel, GameData gameData) {
        for (Entity player : gameData.getEntityList(Type.PLAYER)) {
            if (player.getType() == Type.PLAYER) {

                Point position = player.getPosition();

                AffineTransform transform = player.getSprite().getTransform();
                g.setTransform(transform);
                g.drawImage(player.getSprite().getImage(), position.x, position.y, panel);
            }
        }
    }

    @Override
    public Layer getLayer() {
        return Layer.MIDDLEGROUND;
    }

    @Override
    public String toString() {
        return Type.PLAYER.toString();
    }
}
