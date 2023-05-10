package enemy;

import abstractClasses.Entity;
import interfaces.IGameEngine;
import interfaces.IPlugin;
import utilities.GameData;
import utilities.Types;

import java.io.IOException;
import java.net.URL;

public class EnemyPlugin extends Entity implements IPlugin {
    private Entity lowTierGod;
    private static final URL defaultImage = EnemyPlugin.class.getResource("/enemyImages/ltg.png");

    public EnemyPlugin() throws IOException {
        super(10,defaultImage, Types.ENEMY,new double[]{1,1},1,10);
        setPosition(new int[]{10,10});
    }

    @Override
    public Entity create(GameData gameData) {
        try {
            lowTierGod = new EnemyPlugin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lowTierGod;
    }

    @Override
    public Entity delete(GameData gameData) {
        this.setHealth(0);
        return this;
    }
}
