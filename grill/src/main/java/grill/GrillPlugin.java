package grill;

import abstractClasses.Entity;
import interfaces.IDrawable;
import interfaces.IGameEngine;
import interfaces.IPlugin;
import utilities.Inputs;
import utilities.GameData;
import utilities.Types;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class GrillPlugin implements IPlugin, IDrawable {

    private Entity newGrill;
    private static URL sprite = Entity.class.getClassLoader().getResource("grillImages/placeholder.png");

    public GrillPlugin() {
    }

    @Override
    public Entity create(IGameEngine gameEngine) {
        this.newGrill = new Grill();
        return this.newGrill;
    }

    @Override
    public Entity delete(IGameEngine gameEngine) {
        return null;
    }

    @Override
    public void draw(Graphics2D g, JPanel panel, GameData gameData) {
        for (Entity Grill : gameData.getNewEntities()) {
            if (Grill.getTypes() == Types.WEAPON) {
                int [] position = newGrill.getPosition();
                g.drawImage(newGrill.getSprite().getImage(), position[0], position[1], panel);
            }
        }
    }

    @Override
    public void process(ArrayList<Inputs> inputs, IGameEngine gameEngine) {
        if(inputs.contains(Inputs.KEY_SPACE)) {
            this.shoot();
        }
    }
}
