package gameEngine;

import abstractClasses.Entity;
import interfaces.IDrawable;
import interfaces.IPlugin;
import interfaces.IProcessing;

import utilities.GameData;
import utilities.Inputs;
import utilities.Layers;
import utilities.SPIlocator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class GameEngine{

    private int framerate;
    private UserInputs userInputs;
    private JFrame window;
    private JPanel panel;
    private GameData gameData = new GameData();

    private ScheduledExecutorService gameLoopExecutor;
    private ScheduledExecutorService drawLoopExecutor;

    public GameEngine(int framerate) {
        this.framerate = framerate;
        this.userInputs = new UserInputs();
        this.gameLoopExecutor = Executors.newSingleThreadScheduledExecutor();
        this.drawLoopExecutor = Executors.newSingleThreadScheduledExecutor();
        addEntities();
        addDraw();
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

              for (IDrawable entity : gameData.getDrawables()) {
                  entity.draw(g2d,panel,gameData);
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

    protected void stop(){
        drawLoopExecutor.shutdown();
        gameLoopExecutor.shutdown();
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }

    public void addEntities() {
        for(IPlugin iPlugin : getPlugin()) {
            Entity entity = iPlugin.create(gameData);
            gameData.addNewEntities(entity);
        }
        System.out.println(gameData.getNewEntities().size());

    }

    public JFrame getWindow() {
        return window;
    }

    public int[] getWindowSize(){
        Dimension d =  window.getSize();
        return new int[]{d.width,d.height};
    }

    public GameData getGameData() {
        return gameData;
    }

    private void addDraw(){
        for (IDrawable iDrawable : getIdrawable()){
            gameData.addDrawables(iDrawable);
        }

    }

    private Collection<IDrawable> getIdrawable(){
        return SPIlocator.locateAll(IDrawable.class);
    }

    private Collection<IPlugin> getPlugin(){
        return SPIlocator.locateAll(IPlugin.class);
    }
}
