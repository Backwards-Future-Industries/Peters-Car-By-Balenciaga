package player;

import interfaces.IMovement;
import interfaces.IProcessing;
import utilities.Inputs;
import utilities.SPIlocator;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerMovement implements IProcessing {
        @Override
        public void process(ArrayList<Inputs> inputs) {
            for (IMovement iMovement : getPlugin()){
                setPosition(iMovement.defaultMove(inputs,this));
            }
            this.getSprite().freshRotate(this.getRadians(),this.getPosition());
        }

        private Collection<IMovement> getPlugin(){
            System.out.println(SPIlocator.locateAll(IMovement.class));
            return SPIlocator.locateAll(IMovement.class);
        }
    }

