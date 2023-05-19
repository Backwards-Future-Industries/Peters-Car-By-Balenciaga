package grillBullet;

import abstractClasses.Entity;
import interfaces.IBulletService;
import interfaces.IDrawable;
import utilities.GameData;
import utilities.Layers;
import utilities.Type;
import utilities.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.Arrays;

public class BulletPlugin extends Entity implements IBulletService, IDrawable {
    private static final URL sprite = Bullet.class.getResource("/grillBuletImages/Beef.png");
    Entity bullet;

    @Override
    public Entity create(Entity entity) {
        this.bullet = new Bullet();
        this.bullet.setSprite(sprite, new double[]{1, 1});
        this.bullet.setPosition(getCenter(entity));
        this.bullet.setRadians(entity.getRadians());
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
            //System.out.println("Bullet drawn");
            g.drawImage(bullet.getSprite().getImage(), position[0], position[1], panel);
        }
    }

    private int[] getCenter(Entity entity) {
        int[] skewedPosition = new int[2];
        Vector2D vector = new Vector2D((double) entity.getSprite().getImage().getWidth()/2 * Math.sin(entity.getRadians()), (double) entity.getSprite().getImage().getWidth()/2 * Math.cos(entity.getRadians()));
        Vector2D oVector = new Vector2D(vector.getY(), -vector.getX());
        Vector2D sumVector = new Vector2D(vector.getX() + oVector.getX(), vector.getY() + oVector.getY());
        System.out.println(Arrays.toString(entity.getPosition()));
        skewedPosition[0] = entity.getPosition()[0] + (int) sumVector.getX();
        skewedPosition[1] = entity.getPosition()[1] + (int) sumVector.getY();
        System.out.println(Arrays.toString(skewedPosition));
        return skewedPosition;
    }

    @Override
    public String toString() {
        return Type.BULLET.toString();
    }

    @Override
    public Layers getLayer() {
        return Layers.MIDDLEGROUND;
    }


}
