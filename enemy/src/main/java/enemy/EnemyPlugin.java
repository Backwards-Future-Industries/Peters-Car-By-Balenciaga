package enemy;

import abstractClasses.Entity;
import interfaces.IDrawable;
import interfaces.IPlugin;
import utilities.GameData;
import utilities.Layers;
import utilities.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.Random;

public class EnemyPlugin extends Entity implements IPlugin, IDrawable {
    private static final URL defaultImage = EnemyPlugin.class.getResource("/enemyImages/enemyCar.png");
    private Entity enemy;

    public EnemyPlugin() {
    }

    @Override
    public Entity create() {
        this.enemy = new Enemy();
        this.enemy.setHealth(10);
        Random random = new Random();
        int x = random.nextInt(1000);
        int y = random.nextInt(800);
        this.enemy.setPosition(new int[]{x, y});
        this.enemy.setSprite(defaultImage, new double[]{0.2, 0.2});
        this.enemy.setAcceleration(0.15);
        this.enemy.setMaxSpeed(1);
        this.enemy.setType(Type.ENEMY);


        return this.enemy;
    }

    @Override
    public Entity delete(GameData gameEngine) {
        return null;
    }


    @Override
    public void draw(Graphics2D g, JPanel panel, GameData gameData) {
        for (Entity enemy : gameData.getEntityList(Type.ENEMY)) {
            if (enemy.getType() == Type.ENEMY) {
                int[] position = enemy.getPosition();

                AffineTransform transform = enemy.getSprite().getTransform();
                g.setTransform(transform);
                g.drawImage(enemy.getSprite().getImage(), position[0], position[1], panel);

            }
        }
    }

    @Override
    public Layers getLayer() {
        return Layers.MIDDLEGROUND;
    }

    @Override
    public String toString() {
        return Type.ENEMY.toString();
    }

}
