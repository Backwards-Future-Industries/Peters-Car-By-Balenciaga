package gameEngine;

import abstractClasses.Entity;
import interfaces.IProcessing;
import utilities.Inputs;
import utilities.SPIlocator;
import utilities.Type;

import java.util.ArrayList;
import java.util.Collection;

public class GameLoop implements Runnable {

    private ArrayList<Inputs> inputs;
    private GameEngine gameEngine;

    public GameLoop(ArrayList<Inputs> inputs, GameEngine gameEngine){
        this.inputs = inputs;
        this.gameEngine = gameEngine;
    }

    @Override
    public void run() {

        inputs = gameEngine.getInputs();

        if (inputs.contains(Inputs.KEY_ESC)){
            gameEngine.stop();
            return;
        }
        updateProcess(inputs);

    }




    private void updateProcess(ArrayList<Inputs> inputs){
            for (IProcessing iProcessing : gameEngine.getGameData().getProcesses()){
                iProcessing.process(inputs,gameEngine.getGameData());
            }

    }

}
