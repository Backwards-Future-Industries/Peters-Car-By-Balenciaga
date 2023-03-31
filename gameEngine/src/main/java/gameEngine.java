import interfaces.IDrawable;
import interfaces.IPlugin;
import interfaces.IProcessing;

import java.util.LinkedList;

public class gameEngine {

    private int framerate;


    private LinkedList<IPlugin> newEntities;
    private LinkedList<IProcessing> processes;
    private LinkedList<IDrawable> drawables;

    public gameEngine(int framerate){
        this.framerate = framerate;
        start();
    }

    private void start(){

    }

    private Runnable drawLoop(){
        Runnable runnable = () -> {
                while (true){

                }
        };
        return runnable;
    }
    private void gameLoop(){

    }

}
