package enemy;

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
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class EnemyPlugin implements IPlugin, IDrawable {
    private static final URL defaultImage = EnemyPlugin.class.getResource("/enemyImages/enemyCar.png");
    private Enemy enemy;

    private final LinkedList<Point> spawnPoints = new LinkedList<>(List.of(new Point(100, 200), new Point(300, 300), new Point(500, 200)));

    public EnemyPlugin() {
    }

    @Override
    public Entity create() {
        this.enemy = new Enemy();
        this.enemy.setHealth(10);
        this.enemy.setPosition(spawnPoints.poll());
        this.enemy.setSprite(defaultImage, new double[]{0.2, 0.2});
        this.enemy.setAcceleration(0.15);
        this.enemy.setMaxSpeed(1);
        this.enemy.setType(Type.ENEMY);


        return this.enemy;
    }

    @Override
    public void delete(GameData gameData, Entity entity) {
        gameData.getEntityList(this.enemy.getType()).remove(entity);
    }


    @Override
    public void draw(Graphics2D g, JPanel panel, GameData gameData) {
        for (Entity enemy : gameData.getEntityList(Type.ENEMY)) {
            if (enemy.getType() == Type.ENEMY) {
                Point position = enemy.getPosition();

                AffineTransform transform = enemy.getSprite().getTransform();
                g.setTransform(transform);
                g.drawImage(enemy.getSprite().getImage(), position.x, position.y, panel);

            }
        }
    }

    @Override
    public Layer getLayer() {
        return Layer.MIDDLEGROUND;
    }

    @Override
    public String toString() {
        return Type.ENEMY.toString();
    }

}
