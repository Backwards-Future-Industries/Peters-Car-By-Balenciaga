package gameEngine;

import interfaces.IDrawable;
import interfaces.IGameEngine;
import interfaces.IPlugin;
import interfaces.IProcessing;

import utilities.Inputs;
import utilities.Layers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class GameEngine implements IGameEngine {

    private int framerate;
    private LinkedList<IPlugin> newEntities;
    private LinkedList<IProcessing> processes;
    private LinkedList<IDrawable> foreground;
    private LinkedList<IDrawable> middleground;
    private LinkedList<IDrawable> background;
    private ReentrantLock newLock;
    private ReentrantLock processLock;
    private ReentrantLock drawLock;
    private UserInputs userInputs;
    private JFrame window;
    private JPanel panel;

    private ScheduledExecutorService gameLoopExecutor;
    private ScheduledExecutorService drawLoopExecutor;



    {
        panel = new JPanel();

    }

    public GameEngine(int framerate){
        this.framerate = framerate;
        this.userInputs = new UserInputs();
        this.newEntities = new LinkedList<IPlugin>();
        this.processes = new LinkedList<IProcessing>();
        this.foreground = new LinkedList<IDrawable>();
        this.middleground = new LinkedList<IDrawable>();
        this.background = new LinkedList<IDrawable>();
        this.drawLock = new ReentrantLock(true);
        this.processLock = new ReentrantLock(true);
        this.newLock = new ReentrantLock(true);
        this.gameLoopExecutor = Executors.newSingleThreadScheduledExecutor();
        this.drawLoopExecutor = Executors.newSingleThreadScheduledExecutor();
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
        //https://stackoverflow.com/a/34179907
        drawLoopExecutor.scheduleAtFixedRate(new gameEngine.DrawLoop(this),
                0,1000/framerate,TimeUnit.MILLISECONDS);
        gameLoopExecutor.scheduleAtFixedRate(new gameEngine.GameLoop(new UserInputs().getInputs(),this),
                0,1000/framerate, TimeUnit.MILLISECONDS);
    }

    protected ArrayList<Inputs> getInputs(){
        return userInputs.getInputs();
    }

    protected void updateWindow(){
        panel.repaint();
    }

    @Override
    public long getDeltaDrawTime(){
        return -1;
    }

    @Override
    public long getDeltaProcessTime() {
        return -1;
    }

    public void stop(){
        drawLoopExecutor.shutdown();
        gameLoopExecutor.shutdown();
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
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
            LinkedList<IDrawable> combined = new LinkedList<IDrawable>();
            combined.addAll(background);
            combined.addAll(middleground);
            combined.addAll(foreground);
            return combined;
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
        return addDrawables(draw,Layers.MIDDLEGROUND);
    }
    @Override
    public boolean addDrawables(IDrawable draw, Layers layer) {
        drawLock.lock();
        try {
            if(layer == Layers.BACKGROUND){
                if(this.background.add(draw)){
                    return true;
                }
            }
            if(layer == Layers.MIDDLEGROUND){
                if(this.middleground.add(draw)){
                    return true;
                }
            }
            if(layer == Layers.FOREGROUND){
                if(this.foreground.add(draw)){
                    return true;
                }
            }
        }finally {
            drawLock.unlock();
        }
        return false;
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

    protected void clearNewEntities(){
        newLock.lock();
        try {
            newEntities.clear();
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
    public boolean removeDrawables(IDrawable drawable, Layers layer) {
        drawLock.lock();
        try{
            if(layer == Layers.BACKGROUND){
                if(this.background.remove(drawable)){
                    return true;
                }
            }
            if(layer == Layers.MIDDLEGROUND){
                if(this.middleground.remove(drawable)){
                    return true;
                }
            }
            if(layer == Layers.FOREGROUND){
                if(this.foreground.remove(drawable)){
                    return true;
                }
            }
        }finally {
            drawLock.unlock();
        }
        return false;
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

}
