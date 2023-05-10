package gameEngine;

import interfaces.IDrawable;
import interfaces.IProcessing;
import utilities.Inputs;
import utilities.SPIlocator;

import java.util.ArrayList;
import java.util.Collection;

public class DrawLoop implements Runnable{


    private GameEngine gameEngine;

    public DrawLoop(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    @Override
    public void run() {
        gameEngine.updateWindow();
    }

    /*private void updateDraw(ArrayList<Inputs> inputs){
        for (IDrawable iDrawable : getIprocessing()){
            iDrawable.draw();
        }

    }

    private Collection<IDrawable> getIprocessing(){
        return SPIlocator.locateAll(IDrawable.class);
    }

     */
}
