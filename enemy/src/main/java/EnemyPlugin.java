import abstractClasses.Entity;
import interfaces.IPlugin;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class EnemyPlugin extends Entity implements IPlugin {
    private Entity lowTierGod;
    private static URL sprite = EnemyPlugin.class.getClassLoader().getResource("images/ltg.png");

    protected EnemyPlugin() throws IOException {
        super(10,sprite, new double[]{0.5,0.5});
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
