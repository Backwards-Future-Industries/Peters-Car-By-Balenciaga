import interfaces.IPlugin;
import abstractClasses.Entity;

import java.awt.image.BufferedImage;


public class PlayerPlugin extends Entity implements IPlugin {

    private Entity player;

    public PlayerPlugin() {
        super(5, null);
    }


    private Entity makePlayer(double x, double y){
        Entity playerPlugin = new PlayerPlugin();
        playerPlugin.setPosition(x,y);
        return playerPlugin;
    }

    @Override
    public Entity create() {
        player = makePlayer(10,10);
        return player;
    }


    @Override
    public Entity delete() {
        return null;
    }
}
