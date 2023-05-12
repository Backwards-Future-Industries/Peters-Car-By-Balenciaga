package enemy;

import abstractClasses.Entity;
import interfaces.IDrawable;
import interfaces.IPlugin;
import utilities.GameData;
import utilities.Layers;
import utilities.Types;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class EnemyPlugin extends Entity implements IPlugin, IDrawable {
    private Entity lowTierGod;
    private static final URL defaultImage = EnemyPlugin.class.getResource("/enemyImages/ltg.png");

    public EnemyPlugin() {
    }

    @Override
    public Entity create(GameData gameData) {
        this.lowTierGod = new Enemy();
        this.lowTierGod.setHealth(10);
        this.lowTierGod.setSprite(defaultImage,new double[]{0.1,0.1});
        this.lowTierGod.setAcceleration(1);
        this.lowTierGod.setMaxSpeed(5);
        this.lowTierGod.setType(Types.ENEMY);
        this.lowTierGod.setPosition(new int[]{100,100});

        return this.lowTierGod;
    }

    @Override
    public Entity delete(GameData gameEngine) {
        return null;
    }

    @Override
    public void draw(Graphics2D g, JPanel panel, GameData gameData) {
        for (Entity enemy : gameData.getNewEntities()){
            if (enemy.getType() == Types.ENEMY){
                int[] position = enemy.getPosition();

                AffineTransform transform = enemy.getSprite().getTransform();
                g.setTransform(transform);
                g.drawImage(enemy.getSprite().getImage(),position[0],position[1],panel);

            }
        }
    }

    @Override
    public Layers getLayer() {
        return Layers.MIDDLEGROUND;
    }
}
