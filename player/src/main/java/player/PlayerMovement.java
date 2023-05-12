package player;

import abstractClasses.Entity;
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
               if (player.getType() == Types.PLAYER){
                   for (IMovement iMovement : getPlugin()){
                       player.setPosition(iMovement.defaultMove(inputs,player,gameData));
                   }
                   player.getSprite().freshRotate(player.getRadians(),player.getPosition());
               }
           }
        }

        private Collection<IMovement> getPlugin(){
            return SPIlocator.locateAll(IMovement.class);
        }
    }

