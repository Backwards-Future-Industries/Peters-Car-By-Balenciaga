package player;

import abstractClasses.Entity;
import interfaces.IMovement;
import interfaces.IProcessing;
import utilities.Inputs;
import utilities.SPIlocator;
import utilities.Types;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerMovement implements IProcessing {

    private Types type;

        @Override
        public void process(ArrayList<Inputs> inputs, Entity entity) {
            for (IMovement iMovement : getPlugin()){
                entity.setPosition(iMovement.defaultMove(inputs,entity));
            }
            entity.getSprite().freshRotate(entity.getRadians(),entity.getPosition());
        }

        private Collection<IMovement> getPlugin(){
            System.out.println(SPIlocator.locateAll(IMovement.class));
            return SPIlocator.locateAll(IMovement.class);
        }
    }

