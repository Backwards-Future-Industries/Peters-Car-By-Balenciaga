package gameEngine;

import interfaces.IDrawable;
import interfaces.IGameEngine;
import interfaces.IPlugin;
import interfaces.IProcessing;
import utilities.Inputs;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class GameEngine implements IGameEngine {

    private double framerate;
    private long lastDraw;
    private long lastProcess;
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
        this.gameLoop = new GameLoop(this);
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
              Graphics2D g2d = (Graphics2D) g;
              AffineTransform backup = g2d.getTransform();

              for (IDrawable entity : getDrawables()) {
                  entity.draw(g2d,panel);
                  g2d.setTransform(backup);
              }
          }
        };
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        panel.setSize(screenSize.width,screenSize.height);
        window.setSize(screenSize.width,screenSize.height);
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
    @Override
    public long getDeltaDrawTime(){
        return System.currentTimeMillis() - lastDraw;
    }

    @Override
    public long getDeltaProcessTime() {
        return System.currentTimeMillis() - lastProcess;
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
    @Override
    public LinkedList<IDrawable> getDrawables() {
        drawLock.lock();
        try {
            return drawables;
        }finally {
            drawLock.unlock();
        }
    }
    @Override
    public LinkedList<IPlugin> getNewEntities() {
        newLock.lock();
        try{
            return newEntities;
        } finally {
            newLock.unlock();
        }
    }
    @Override
    public LinkedList<IProcessing> getProcesses() {
        processLock.lock();
        try {
            return processes;
        }finally {
            processLock.unlock();
        }
    }
    @Override
    public boolean addDrawables(IDrawable draw) {
        drawLock.lock();
        try {
            if (this.drawables.add(draw)){
                return true;
            }else{
                return false;
            }
        }
        finally {
            drawLock.unlock();
        }
    }
    @Override
    public boolean addNewEntities(IPlugin newEntity) {
        newLock.lock();
        try {
            if (this.newEntities.add(newEntity)){
                return true;
            }else{
                return false;
            }
        }finally {
            newLock.unlock();
        }
    }
    @Override
    public boolean addProcesses(IProcessing process) {
        processLock.lock();
        try {
            if (this.processes.add(process)){
                return true;
            }else{
                return false;
            }
        }finally {
            processLock.unlock();
        }
    }
    @Override
    public boolean removeDrawables(IDrawable drawable) {
        drawLock.lock();
        try{
            if (drawables.remove(drawable)){
                return true;
            }else{
                return false;
            }
        }finally {
            drawLock.unlock();
        }
    }
    @Override
    public boolean removeProcesses(IProcessing process) {
        processLock.lock();
        try {
            if (processes.remove(process)){
                return true;
            }else {
                return false;
            }
        }finally {
            processLock.lock();
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
                while (1.0/(getDeltaDrawTime()*1000.0) <= framerate){
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
        IGameEngine gameEngine;
        public GameLoop(IGameEngine gameEngine){
            this.isRunning = true;
            this.gameEngine = gameEngine;
        }
        @Override
        public void run() {
            lastProcess = 0;
            while (isRunning) {
                inputs = userInputs.getInputs();
                for(IPlugin newEntity : getNewEntities()){
                    newEntity.create(gameEngine);
                }
                getNewEntities().clear();
                for(IProcessing entity : getProcesses()){
                    entity.process(inputs, gameEngine);
                }
                lastProcess = System.currentTimeMillis();
            }
        }

        public void kill(){
            this.isRunning = false;
        }
    }
}
