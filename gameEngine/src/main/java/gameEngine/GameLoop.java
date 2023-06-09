package gameEngine;

import interfaces.IProcessing;
import utilities.Inputs;

import java.util.ArrayList;

public class GameLoop implements Runnable {

    private ArrayList<Inputs> inputs;
    private GameEngine gameEngine;

    public GameLoop(ArrayList<Inputs> inputs, GameEngine gameEngine) {
        this.inputs = inputs;
        this.gameEngine = gameEngine;
    }

    @Override
    public void run() {
        inputs = gameEngine.getInputs();

        if (inputs.contains(Inputs.KEY_ESC)) {
            gameEngine.stop();
            return;
        }
        updateProcess(inputs);
    }


    private void updateProcess(ArrayList<Inputs> inputs) {
        for (IProcessing iProcessing : gameEngine.getGameData().getProcesses()) {
            iProcessing.process(inputs, gameEngine.getGameData());
        }

    }

}
