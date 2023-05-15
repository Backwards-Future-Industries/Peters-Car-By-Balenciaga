package grillBullet;

import abstractClasses.Entity;
import interfaces.IBulletService;
import interfaces.IDrawable;
import interfaces.IMovement;
import utilities.GameData;
import utilities.Layers;
import utilities.SPIlocator;
import utilities.Types;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class BulletPlugin implements IBulletService, IDrawable {
    Entity bullet;

    private static final URL sprite = Bullet.class.getResource("/grillBuletImages/bullet.png");

    @Override
    public Entity create(Entity entity) {
        this.bullet = new Bullet();
        this.bullet.setSprite(sprite,new double[]{0.1,0.1});
        this.bullet.setPosition(entity.getPosition());
        this.bullet.setRadians(entity.getRadians());
        //this.bullet
        this.bullet.setMaxSpeed(10);
        this.bullet.setAcceleration(10);
        this.bullet.setTypes(Types.BULLET);


        return this.bullet;
    }

    @Override
    public void delete(GameData gameData) {

    }

    @Override

    public void draw(Graphics2D g, JPanel panel, GameData gameData) {
        for(Entity bullet : gameData.getEntityMap(Types.BULLET)){

                int [] position = bullet.getPosition();

                AffineTransform transform = bullet.getSprite().getTransform();
                g.setTransform(transform);
                g.drawImage(bullet.getSprite().getImage(),position[0],position[1],panel);
            }
        }
    }

    @Override
    public String toString(){
        return Types.BULLET.toString();
    }

    @Override
    public Layers getLayer() {
        return Layers.MIDDLEGROUND;
    }
}
