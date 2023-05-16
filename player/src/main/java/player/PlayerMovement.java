package player;

import abstractClasses.Entity;
import interfaces.IBulletService;
import interfaces.IDrawable;
import interfaces.IMovement;
import interfaces.IProcessing;
import utilities.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class PlayerMovement implements IProcessing {

    private LinkedList<IBulletService> bullets;

    @Override
    public void process(ArrayList<Inputs> inputs, GameData gameData) {
        for (Entity player : gameData.getEntityList(Type.PLAYER)) {
            if (inputs.contains(Inputs.KEY_SPACE)) {
              gameData.addBullet(Type.BULLET,player);
            }

            player.setPosition(SPIlocator.getSpIlocator().getMovement().defaultMove(inputs, player, gameData));
            player.getSprite().freshRotate(player.getRadians(), player.getPosition());
        }
    }


    @Override
    public String toString() {
        return Type.PLAYER.toString();
    }
}

