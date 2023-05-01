package gameEngine;

import gameEngine.GameEngine;
import interfaces.IPlugin;
import interfaces.IProcessing;
import utilities.Inputs;

import java.util.ArrayList;

public class GameLoop implements Runnable {

    private ArrayList<Inputs> inputs;
    private GameEngine gameEngine;

    public GameLoop(ArrayList<Inputs> inputs, GameEngine gameEngine){
        this.inputs = inputs;
        this.gameEngine = gameEngine;
    }

    @Override
    public void run() {

        for(IPlugin newEntity : gameEngine.getNewEntities()){
            newEntity.create(gameEngine);
        }

        for(IProcessing entity : gameEngine.getProcesses()){
            entity.process(inputs, gameEngine);
        }

    }
}
