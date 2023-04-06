import interfaces.IDrawable;
import interfaces.IPlugin;
import interfaces.IProcessing;
import utilities.Inputs;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameEngine {

    private double framerate;
    private long lastDraw;
    private LinkedList<IPlugin> newEntities;
    private LinkedList<IProcessing> processes;
    private LinkedList<IDrawable> drawables;
    private ArrayList<Inputs> inputs;

    private UserInputs userInputs;
    private  JFrame window;

    public GameEngine(double framerate){
        this.framerate = framerate;
        this.userInputs = new UserInputs();
        this.inputs = new ArrayList<Inputs>();
        openWindow();
        start();
    }

    private void openWindow(){
        window = new JFrame();
        window.setSize(1000,1000);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Peter's car");
        window.addKeyListener(userInputs);

        window.setVisible(true);
    }


    private void start(){
        DrawLoop drawLoop = new DrawLoop();
        GameLoop gameLoop = new GameLoop();
        drawLoop.start();
        gameLoop.start();
    }


    private double getDeltaTime(){
        return System.currentTimeMillis() - lastDraw;
    }

    private class DrawLoop extends Thread{
        public DrawLoop(){
        }
        @Override
        public void run() {
            lastDraw = 0;
            while (true) {
                while (1.0/(getDeltaTime()*1000.0) <= framerate){
                    for (IDrawable entity : drawables) {
                        entity.draw();
                    }
                    lastDraw = System.currentTimeMillis();
                }
            }
        }
    }

    private class GameLoop extends Thread{

        public GameLoop(){}
        @Override
        public void run() {
            while (true) {
                inputs = userInputs.getInputs();
                for(IPlugin newEntity : newEntities){
                    newEntity.create();
                }
                newEntities.clear();
                for(IProcessing entity : processes){
                    entity.process(inputs);
                }
            }
        }
    }
}
