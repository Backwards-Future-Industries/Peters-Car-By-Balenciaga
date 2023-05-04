package grill;

import abstractClasses.Entity;
import abstractClasses.Weapon;
import interfaces.IDrawable;
import interfaces.IPlugin;
import interfaces.IProcessing;
import utilities.GameData;
import utilities.Inputs;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class Grill extends Weapon implements IDrawable, IProcessing, IPlugin{

    private static URL sprite = Entity.class.getClassLoader().getResource("grillImages/placeholder.png");

    public Grill(){
        super(1,sprite);
        setPosition(new int[]{50, 50});
    }

    public Grill(int health) {
        super(health, sprite);
        setPosition(new int[]{50, 50});
    }

    @Override
    public void draw(Graphics2D g, JPanel panel) {
        int [] position = getPosition();
        g.drawImage(getSprite().getImage(), position[0], position[1], panel);
    }

    @Override
    public void shoot(){
        //Bullet bullet = new Bullet();
        //bullet.create();
    }

    @Override
    public Entity create(GameData gameEngine) {
        Entity newGrill;
        newGrill = new Grill(1);
        return newGrill;
    }

    @Override
    public Entity delete(GameData gameEngine) {
        return null;
    }

    @Override
    public void process(ArrayList<Inputs> inputs) {
        if(inputs.contains(Inputs.KEY_SPACE)) {
            this.shoot();
        }
    }
}
