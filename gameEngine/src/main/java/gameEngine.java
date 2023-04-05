import interfaces.IDrawable;
import interfaces.IPlugin;
import interfaces.IProcessing;
import java.util.LinkedList;

public class gameEngine {

    private double framerate;
    private LinkedList<IPlugin> newEntities;
    private LinkedList<IProcessing> processes;
    private LinkedList<IDrawable> drawables;

    public gameEngine(double framerate){
        this.framerate = framerate;
        start();
    }

    private void start(){
        DrawLoop drawLoop = new DrawLoop(framerate,drawables);
        GameLoop gameLoop = new GameLoop(newEntities,processes);
        drawLoop.start();
        gameLoop.start();
    }

}
