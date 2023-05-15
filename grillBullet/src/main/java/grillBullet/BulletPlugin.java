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

public class BulletPlugin implements IBulletService, IDrawable {
    Entity bullet;

    private static final URL sprite = Bullet.class.getResource("/grillBuletImages/bullet.png");

    @Override
    public Entity create(int[] position, double radians) {
        this.bullet = new Bullet();
        this.bullet.setSprite(sprite,new double[]{0.5,0.5});
        this.bullet.setPosition(new int[]{1,1});
        this.bullet.setRadians(2);
        this.bullet.setMaxSpeed(3);
        this.bullet.setAcceleration(1);
        this.bullet.setType(Types.BULLET);

        return this.bullet;
    }


    @Override
    public void delete(GameData gameData) {

    }

    @Override

         */
    }

    @Override
    public void draw(Graphics2D g, JPanel panel, GameData gameData) {
        for(Entity bullet : gameData.getEntityList(Type.BULLET)){

                int [] position = bullet.getPosition();

                AffineTransform transform = bullet.getSprite().getTransform();
                g.setTransform(transform);
                g.drawImage(bullet.getSprite().getImage(),position[0],position[1],panel);
        }

    @Override
    public String toString(){
        return Type.BULLET.toString();
    }

    @Override
    public Layers getLayer() {
        return Layers.MIDDLEGROUND;
    }

    private Collection<IMovement> getPlugin() {
        return SPIlocator.locateAll(IMovement.class);
    }
}
