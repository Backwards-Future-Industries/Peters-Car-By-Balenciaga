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

public class BulletPlugin extends Entity implements IBulletService, IDrawable {
    private static final URL sprite = Bullet.class.getResource("/grillBuletImages/Beef.png");
    Entity bullet;

    @Override
    public Entity create(Entity entity) {
        this.bullet = new Bullet();
        this.bullet.setSprite(sprite, new double[]{1, 1});
        this.bullet.setPosition(getCenter(entity));
        this.bullet.setRadians(entity.getRadians());
        this.bullet.setDirection(new Vector2D(entity.getDirection().getX()+1, entity.getDirection().getY()+1));
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

        Point[] points = entity.getSprite().updateTransformedRectangle(entity.getPosition()[0], entity.getPosition()[1], entity.getRadians());
        double length = entity.getSprite().getImage().getWidth()+20;
        Vector2D vector = new Vector2D(length * Math.cos(entity.getRadians()), length * Math.sin(entity.getRadians()));
        Point centerAB = new Point((points[1].x + points[0].x) / 2, (points[1].y + points[0].y) / 2);
        Point centerCD = new Point((points[3].x + points[2].x) / 2, (points[3].y + points[2].y) / 2);
        Point center = new Point((centerAB.x + centerCD.x) / 2, (centerAB.y + centerCD.y) / 2);

        skewedPosition[0] = (int) (center.x + vector.getX());
        skewedPosition[1] = (int) (center.y + vector.getY());

        //This is for debugging it generates a geogebra script that can be used to visualize entities points and the bullets spawn point and direction
//        System.out.print("Execute[{");
//        int counter = 0;
//        String[] pointNames = {"A", "B", "C", "D"};
//        for (Point point : points) {
//            System.out.print("\""+pointNames[counter] + "=" + "(" + point.getX() + ", " + point.getY() + ")\",");
//            counter++;
//        }
//        System.out.print("\"CenterAB=" + "(" + centerAB.getX() + ", " + centerAB.getY() + ")\",");
//        System.out.print("\"CenterCD=" + "(" + centerCD.getX() + ", " + centerCD.getY() + ")\",");
//        System.out.print("\"Center=" + "(" + center.getX() + ", " + center.getY() + ")\",");
//        System.out.print("\"Entity=" + "(" + entity.getPosition()[0] + ", " + entity.getPosition()[1] + ")\",");
//        System.out.print("\"Bullet=" + "(" + skewedPosition[0] + ", " + skewedPosition[1] + ")\"}]");
//        System.out.println();
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
