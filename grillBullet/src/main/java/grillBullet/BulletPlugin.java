package grillBullet;

import abstractClasses.Entity;
import interfaces.IBulletService;
import interfaces.IDrawable;
import utilities.GameData;
import utilities.Layers;
import utilities.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.Collection;

public class BulletPlugin extends Entity implements IBulletService, IDrawable {
    Entity bullet;

    private static final URL sprite = Bullet.class.getResource("/grillBuletImages/Beef.png");

    @Override
    public Entity create(int[] position, double radians) {
        this.bullet = new Bullet();
        this.bullet.setSprite(sprite, new double[]{1, 1});
        this.bullet.setPosition(position);
        this.bullet.setRadians(radians);
        this.bullet.setMaxSpeed(10);
        this.bullet.setAcceleration(10);
        this.bullet.setType(Type.BULLET);

        return this.bullet;
    }


    @Override
    public void delete(GameData gameData) {

    }

    @Override
    public void draw(Graphics2D g, JPanel panel, GameData gameData) {
        for (Entity bullet : gameData.getEntityList(Type.BULLET)) {

            int[] position = bullet.getPosition();

            AffineTransform transform = bullet.getSprite().getTransform();
            g.setTransform(transform);
            g.drawImage(bullet.getSprite().getImage(), position[0], position[1], panel);
        }
    }

        @Override
        public String toString () {
            return Type.BULLET.toString();
        }

        @Override
        public Layers getLayer () {
            return Layers.MIDDLEGROUND;
        }


}
