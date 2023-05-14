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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static utilities.Inputs.KEY_W;

public class BulletPlugin implements IBulletService, IDrawable {
    Entity bullet;

    private static final URL sprite = Bullet.class.getResource("/grillBuletImages/bullet.png");

    @Override
    public Entity create(Entity entity) {
        this.bullet = new Bullet();
        this.bullet.setSprite(sprite,new double[]{0.5,0.5});
        this.bullet.setPosition(new int[]{1,1});
        this.bullet.setRadians(2);
        this.bullet.setMaxSpeed(3);
        this.bullet.setAcceleration(1);
        this.bullet.setTypes(Types.BULLET);

        return this.bullet;
    }

    @Override
    public void delete(GameData gameData) {

    }

    @Override
    public void process(GameData gameData) {
        /*for (Entity bullet : gameData.getNewEntities()) {
            if (bullet.getTypes() == Types.BULLET) {
                for (IMovement iMovement : getPlugin()) {
                    bullet.setPosition(iMovement.defaultMove(new ArrayList<>(List.of(KEY_W)), bullet));
                }
            }
        }

         */
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

    @Override
    public String toString(){
        return Types.BULLET.toString();
    }

    @Override
    public Layers getLayer() {
        return Layers.MIDDLEGROUND;
    }

    private Collection<IMovement> getPlugin() {
        return SPIlocator.locateAll(IMovement.class);
    }
}
