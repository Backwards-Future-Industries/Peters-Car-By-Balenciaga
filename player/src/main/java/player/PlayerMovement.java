package player;

import abstractClasses.Entity;
import interfaces.IBulletService;
import interfaces.IMovement;
import interfaces.IProcessing;
import utilities.GameData;
import utilities.Inputs;
import utilities.SPIlocator;
import utilities.Types;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerMovement implements IProcessing {

        @Override
        public void process(ArrayList<Inputs> inputs, GameData gameData) {
           for (Entity player : gameData.getNewEntities()){
               if (player.getTypes() == Types.PLAYER){
                   if(inputs.contains(Inputs.KEY_SPACE)) {
                       for (IBulletService bullet : getBullet()) {
                           gameData.addNewEntities(bullet.create(player));
                       }

                   }

                   for (IMovement iMovement : getMovement()){
                       player.setPosition(iMovement.defaultMove(inputs,player));
                   }
                   player.getSprite().freshRotate(player.getRadians(),player.getPosition());
               }
           }
        }

        private Collection<IMovement> getMovement(){
            return SPIlocator.locateAll(IMovement.class);
        }

        private Collection<IBulletService> getBullet(){
            return SPIlocator.locateAll(IBulletService.class);
        }
    }

