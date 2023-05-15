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

                for (IBulletService bullet : getBullet()) {
                    gameData.addNewEntity(bullet.create(player.getPosition(),player.getRadians()));
                }



                    for (IDrawable iDrawable : getBulletDraw()) {
                        if (iDrawable.toString().equals(Type.BULLET.toString())) {
                            gameData.addDrawables(iDrawable);
                        }
                    }
            }


            for (IMovement iMovement : getMovement()) {
                player.setPosition(iMovement.defaultMove(inputs, player, gameData));
            }
            player.getSprite().freshRotate(player.getRadians(), player.getPosition());
        }
    }

    private Collection<IMovement> getMovement() {
        return SPIlocator.locateAll(IMovement.class);
    }

    private Collection<IBulletService> getBullet() {
        return SPIlocator.locateAll(IBulletService.class);
    }

    private Collection<IDrawable> getBulletDraw() {
        return SPIlocator.locateAll(IDrawable.class);
    }


    @Override
    public String toString(){
        return Type.PLAYER.toString();
    }
}

