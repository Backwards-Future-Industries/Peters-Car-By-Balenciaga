package enemy;

import abstractClasses.Entity;
import interfaces.*;
import utilities.GameData;
import utilities.Inputs;
import utilities.SPIlocator;
import utilities.Types;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

public class EnemyPlugin extends Entity implements IPlugin, IDrawable, IProcessing {
    private Entity lowTierGod;
    private AIMovement aiMovement;
    private static final URL defaultImage = EnemyPlugin.class.getResource("/enemyImages/ltg.png");

    public EnemyPlugin() throws IOException {
        super(10,defaultImage,new double[]{0.5,0.5},1,10, Types.ENEMY);
        setPosition(new int[]{10,10});
        setRadians(0);
    }
    @Override
    public Entity create(GameData gameData) {
        try {
            lowTierGod = new EnemyPlugin();
            aiMovement = new AIMovement(gameData,lowTierGod);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gameData.addDrawables((IDrawable) lowTierGod);
        gameData.addProcesses((IProcessing) lowTierGod);

        return lowTierGod;
    }

    @Override
    public Entity delete(GameData gameEngine) {
        return null;
    }

    @Override
    public void draw(Graphics2D g, JPanel panel) {
        int[] position = getPosition();

        AffineTransform transform = getSprite().getTransform();
        g.setTransform(transform);
        g.drawImage(getSprite().getImage(),position[0],position[1],panel);

    }

    @Override
    public void process(ArrayList<Inputs> inputs) {
        for (IMovement iMovement : getPlugin()){
            setPosition(iMovement.defaultMove(inputs,this));
        }
        this.getSprite().freshRotate(this.getRadians(),this.getPosition());

    }
    private Collection<IMovement> getPlugin(){
        return SPIlocator.locateAll(IMovement.class);
    }
}
