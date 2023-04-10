package gameEngine;

import interfaces.IDrawable;
import interfaces.IPlugin;
import interfaces.IProcessing;
import utilities.Inputs;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class GameEngine {

    private double framerate;
    private long lastDraw;
    private LinkedList<IPlugin> newEntities;
    private LinkedList<IProcessing> processes;
    private LinkedList<IDrawable> drawables;
    private ReentrantLock newLock;
    private ReentrantLock processLock;
    private ReentrantLock drawLock;
    private DrawLoop drawLoop;
    private GameLoop gameLoop;
    private ArrayList<Inputs> inputs;
    private UserInputs userInputs;
    private JFrame window;

    public GameEngine(double framerate){
        this.framerate = framerate;
        this.userInputs = new UserInputs();
        this.inputs = new ArrayList<Inputs>();
        this.newEntities = new LinkedList<IPlugin>();
        this.processes = new LinkedList<IProcessing>();
        this.drawables = new LinkedList<IDrawable>();
        this.drawLoop = new DrawLoop();
        this.gameLoop = new GameLoop();
        this.drawLock = new ReentrantLock(true);
        this.processLock = new ReentrantLock(true);
        this.newLock = new ReentrantLock(true);
        openWindow();
        start();
    }

    private void openWindow(){
        window = new JFrame();
        window.setSize(500,500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Peter's car");
        window.addKeyListener(userInputs);
        window.setVisible(true);
    }

    private void start(){
        drawLoop.start();
        gameLoop.start();
    }


    private double getDeltaTime(){
        return System.currentTimeMillis() - lastDraw;
    }

    public void stop(){
        drawLoop.kill();
        gameLoop.kill();
    }

    public JFrame getWindow() {
        return window;
    }

    public LinkedList<IDrawable> getDrawables() {
        synchronized(drawLock){
            return drawables;
        }
    }
    public LinkedList<IPlugin> getNewEntities() {
        synchronized (newLock){
            return newEntities;
        }
    }
    public LinkedList<IProcessing> getProcesses() {
        synchronized (processLock){
            return processes;
        }
    }
    public void addDrawables(IDrawable iDrawable) {
        synchronized (drawLock){
            this.drawables.add(iDrawable);
        }
    }
    public void addNewEntities(IPlugin newEntity) {
        synchronized (newLock){
            this.newEntities.add(newEntity);
        }
    }
    public void addProcesses(IProcessing process) {
        synchronized (processLock){
            this.processes.add(process);
        }
    }

    private class DrawLoop extends Thread{
       private boolean isRunning;

        public DrawLoop(){
            this.isRunning = true;
        }
        @Override
        public void run() {
            lastDraw = 0;
            while (isRunning) {
                while (1.0/(getDeltaTime()*1000.0) <= framerate){
                    for (IDrawable entity : getDrawables()) {
                        entity.draw();
                    }
                    lastDraw = System.currentTimeMillis();
                }
            }
        }

        public void kill(){
            this.isRunning = false;
        }
    }

    private class GameLoop extends Thread{
        private boolean isRunning;
        public GameLoop(){
            this.isRunning = false;
        }
        @Override
        public void run() {
            while (isRunning) {
                inputs = userInputs.getInputs();
                for(IPlugin newEntity : getNewEntities()){
                    newEntity.create();
                }
                getNewEntities().clear();
                for(IProcessing entity : getProcesses()){
                    entity.process(inputs);
                }
            }
        }

        public void kill(){
            this.isRunning = false;
        }
    }
}
