package gameEngine;

import abstractClasses.Entity;
import interfaces.IDrawable;
import interfaces.IPlugin;

import utilities.GameData;
import utilities.Inputs;
import utilities.SPIlocator;
import utilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameEngine{

    private int framerate;
    private UserInputs userInputs;
    private JFrame window;
    private JPanel panel;
    private GameData gameData;

    private ScheduledExecutorService gameLoopExecutor;
    private ScheduledExecutorService drawLoopExecutor;

    public GameEngine(int framerate) {
        this.framerate = framerate;
        this.userInputs = new UserInputs();
        this.gameLoopExecutor = Executors.newSingleThreadScheduledExecutor();
        this.drawLoopExecutor = Executors.newSingleThreadScheduledExecutor();
        this.gameData = new GameData();
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
        Dimension screenSize = new Dimension(1280,960);
        gameData.setScreenSize(screenSize);
        panel.setPreferredSize(screenSize);
        panel.setSize(screenSize);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Peter's car");
        window.add(panel);
        window.addKeyListener(userInputs);
        window.pack();
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
            if(entity.getTypes() != Types.BULLET){
                gameData.addNewEntities(entity);
                System.out.println(entity);
            }

        }
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
            if (iDrawable.toString() == Types.BULLET.toString()) {
                continue;
            }
            gameData.addDrawables(iDrawable, iDrawable.getLayer());
            System.out.println(iDrawable);
        }


    }

    private Collection<IDrawable> getIdrawable(){
        return SPIlocator.locateAll(IDrawable.class);
    }

    private Collection<IPlugin> getPlugin(){
        return SPIlocator.locateAll(IPlugin.class);
    }
}
