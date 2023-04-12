package gameEngine;

import interfaces.IDrawable;
import interfaces.IPlugin;
import interfaces.IProcessing;
import utilities.Inputs;

import javax.swing.*;
import java.awt.*;
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
    private JPanel panel;

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
        this.panel = new JPanel(){
          @Override
          public void paintComponent(Graphics g) {
              super.paintComponent(g);
              for (IDrawable entity : getDrawables()) {
                  entity.draw(g);
              }
          }
        };
        panel.setSize(500,500);
        window.setSize(500,500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Peter's car");
        window.add(panel);
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

    public int[] getWindowSize(){
        Dimension d =  window.getSize();
        return new int[]{d.width,d.height};
    }

    public LinkedList<IDrawable> getDrawables() {
        drawLock.lock();
        try {
            return drawables;
        }finally {
            drawLock.unlock();
        }
    }
    public LinkedList<IPlugin> getNewEntities() {
        newLock.lock();
        try{
            return newEntities;
        } finally {
            newLock.unlock();
        }
    }
    public LinkedList<IProcessing> getProcesses() {
        processLock.lock();
        try {
            return processes;
        }finally {
            processLock.unlock();
        }
    }
    public void addDrawables(IDrawable draw) {
        drawLock.lock();
        try {
            this.drawables.add(draw);
        }finally {
            drawLock.unlock();
        }
    }
    public void addNewEntities(IPlugin newEntity) {
        newLock.lock();
        try {
            this.newEntities.add(newEntity);
        }finally {
            newLock.unlock();
        }
    }
    public void addProcesses(IProcessing process) {
        processLock.lock();
        try {
            this.processes.add(process);
        }finally {
            processLock.unlock();
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
                    panel.repaint();
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
            this.isRunning = true;
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
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void kill(){
            this.isRunning = false;
        }
    }
}
