package grillBullet;

import abstractClasses.Entity;
import interfaces.IBulletService;
import interfaces.IDrawable;
import utilities.GameData;
import utilities.Layers;
import utilities.Types;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BulletPlugin implements IBulletService, IDrawable {
    Entity bullet;

    private static final URL sprite = Bullet.class.getResource("images/bullet.png");

    @Override
    public Entity create(Entity entity) {
        this.bullet = new Bullet();
        this.bullet.setSprite(sprite,new double[]{0.5,0.5});
        bullet.setPosition(entity.getPosition());
        bullet.setRadians(entity.getRadians());
        bullet.setDirection(entity.getDirection());
        bullet.setMaxSpeed(3);
        bullet.setAcceleration(1);
        this.bullet.setTypes(Types.BULLET);

        return this.bullet;
    }

    @Override
    public void delete(GameData gameData) {

    }

    @Override
    public void draw(Graphics2D g, JPanel panel, GameData gameData) {
        int[] position = bullet.getPosition();
        g.drawImage(bullet.getSprite().getImage(), position[0], position[1], panel);
    }

    @Override
    public Layers getLayer() {
        return Layers.MIDDLEGROUND;
    }
}
