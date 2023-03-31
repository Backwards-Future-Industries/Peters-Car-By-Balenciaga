import interfaces.IPlugin;
import abstractClasses.Entity;

import java.awt.image.BufferedImage;


public class PlayerPlugin extends Entity implements IPlugin {

    public PlayerPlugin(int health, BufferedImage sprite) {
        super(health, sprite);
    }


    private Entity makePlayer(int health, BufferedImage sprite,double x, double y){
        Entity playerPlugin = new PlayerPlugin(health,sprite);
        playerPlugin.setPosition(x,y);

        return playerPlugin;
    }

    @Override
    public Entity create() {
        return null;
    }


    @Override
    public Entity delete() {
        return null;
    }
}
