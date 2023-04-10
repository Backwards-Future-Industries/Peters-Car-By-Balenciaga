import interfaces.IPlugin;
import abstractClasses.Entity;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class PlayerPlugin extends Entity implements IPlugin {

    private Entity player;
    private static URL sprite = PlayerPlugin.class.getClassLoader().getResource("images/blueCar.png");

    PlayerPlugin() throws IOException {
        super(5, ImageIO.read(sprite));
        setPosition(10,10);
    }

    @Override
    public Entity create() {
        Entity newPlayer;
        try {
            newPlayer = new PlayerPlugin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newPlayer;
    }


    @Override
    public Entity delete() {
        return null;
    }
}
