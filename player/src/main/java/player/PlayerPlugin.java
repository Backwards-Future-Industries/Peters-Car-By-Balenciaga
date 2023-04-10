package player;

import interfaces.IDrawable;
import interfaces.IPlugin;
import abstractClasses.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;


public class PlayerPlugin extends Entity implements IPlugin, IDrawable {

    private Entity player;
    private static URL sprite = PlayerPlugin.class.getClassLoader().getResource("images/blueCar.png");

    public PlayerPlugin() throws IOException {
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

    @Override
    public void draw() {

    }
}
