package gameEngine;

public class DrawLoop implements Runnable {

    private GameEngine gameEngine;

    public DrawLoop(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void run() {
        gameEngine.updateWindow();
    }
}
