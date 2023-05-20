package bullet;

import abstractClasses.Entity;
import interfaces.IBulletService;
import interfaces.IDrawable;
import utilities.GameData;
import utilities.Layer;
import utilities.Type;
import utilities.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class BulletPlugin implements IBulletService, IDrawable {
    private static final URL sprite = Bullet.class.getResource("/grillBuletImages/Beef.png");
    Bullet bullet;

    @Override
    public Entity create(Entity entity) {
        this.bullet = new Bullet();
        this.bullet.setHealth(2);
        this.bullet.setSprite(sprite, new double[]{1, 1});
        this.bullet.setPosition(getCenter(entity));
        this.bullet.setRadians(entity.getRadians());
        this.bullet.setDirection(new Vector2D(entity.getDirection().getX() + 1, entity.getDirection().getY() + 1));
        this.bullet.setMaxSpeed(10);
        this.bullet.setAcceleration(10);
        this.bullet.setType(Type.BULLET);

        return this.bullet;
    }


    @Override
    public void delete(GameData gameData, Entity entity) {
        gameData.getEntityList(this.bullet.getType()).remove(entity);
    }

    @Override
    public void draw(Graphics2D g, JPanel panel, GameData gameData) {
        for (Entity bullet : gameData.getEntityList(Type.BULLET)) {

            Point position = bullet.getPosition();

            AffineTransform transform = bullet.getSprite().getTransform();
            g.setTransform(transform);
            g.drawImage(bullet.getSprite().getImage(), position.x, position.y, panel);
        }
    }

    private Point getCenter(Entity entity) {
        Point skewedPosition = new Point(0, 0);

        Point[] points = entity.getSprite().updateTransformedRectangle(entity.getPosition().x, entity.getPosition().y, entity.getRadians());
        double length = entity.getSprite().getImage().getWidth() + 20;
        Vector2D vector = new Vector2D(length * Math.cos(entity.getRadians()), length * Math.sin(entity.getRadians()));
        Point centerAB = new Point((points[1].x + points[0].x) / 2, (points[1].y + points[0].y) / 2);
        Point centerCD = new Point((points[3].x + points[2].x) / 2, (points[3].y + points[2].y) / 2);
        Point center = new Point((centerAB.x + centerCD.x) / 2, (centerAB.y + centerCD.y) / 2);

        skewedPosition.x = (int) (center.x + vector.getX());
        skewedPosition.y = (int) (center.y + vector.getY());

        return skewedPosition;
    }

    @Override
    public String toString() {
        return Type.BULLET.toString();
    }

    @Override
    public Layer getLayer() {
        return Layer.MIDDLEGROUND;
    }


}
