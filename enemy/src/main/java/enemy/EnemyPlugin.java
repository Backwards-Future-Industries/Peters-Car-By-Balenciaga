package enemy;

import abstractClasses.Entity;
import interfaces.IPlugin;

import java.io.IOException;
import java.net.URL;

public class EnemyPlugin extends Entity implements IPlugin {
    private Entity lowTierGod;
    private static URL defaultImage = EnemyPlugin.class.getClassLoader().getResource("images/ltg.png");

    public EnemyPlugin() throws IOException {
        super(10,defaultImage);
        setPosition(new int[]{10,10});
    }

    @Override
    public Entity create() {
        try {
            lowTierGod = new EnemyPlugin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lowTierGod;
    }

    @Override
    public Entity delete() {
        this.setHealth(0);
        return this;
    }
}
