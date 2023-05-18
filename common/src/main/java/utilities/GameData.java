package utilities;

import abstractClasses.CommonMap;
import abstractClasses.Entity;
import interfaces.IDrawable;
import interfaces.IPlugin;
import interfaces.IProcessing;

import java.awt.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class GameData {
    private Map<Type,LinkedList<Entity>> entityMap;
    private List<IProcessing> processes;
    private List<IDrawable> foreground;
    private List<IDrawable> middleground;
    private List<IDrawable> background;
    private ReentrantLock newLock;
    private ReentrantLock processLock;
    private ReentrantLock drawLock;

    private ReentrantLock addComponentLock;
    private ReentrantLock mapLock;

    private Dimension screenSize;
    private CommonMap map;
    private SPIlocator spiLocator;

    public GameData(){
        this.entityMap = new HashMap<Type,LinkedList<Entity>>();
        createMap();
        this.processes = new LinkedList<IProcessing>();
        this.foreground = new LinkedList<IDrawable>();
        this.middleground = new LinkedList<IDrawable>();
        this.background = new LinkedList<IDrawable>();
        this.drawLock = new ReentrantLock(true);
        this.processLock = new ReentrantLock(true);
        this.newLock = new ReentrantLock(true);
        this.addComponentLock = new ReentrantLock(true);
        this.mapLock = new ReentrantLock(true);
        this.spiLocator = SPIlocator.getSpIlocator();
        addAllProcess();
    }

    private void createMap(){
        for (Type type : Type.values()){
            this.entityMap.put(type,new LinkedList<Entity>());
        }
    }


    /**
     * @return List of all entities that gets drawn.
     */
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

    public void addAllProcess(){
        for(Type type : Type.values()){
            IProcessing iProcessing = spiLocator.getProcessingMap().get(type);
            if(iProcessing != null) {
                processes.add(spiLocator.getProcessingMap().get(type));
            }
        }
    }

    public void  AddComponent(Type type){
        addComponentLock.lock();
        try {
            Layers layer = spiLocator.getiDrawableMap().get(type).getLayer();
            addDrawables(spiLocator.getiDrawableMap().get(type),layer);

            Entity entity = spiLocator.getPluginMap().get(type).create();
            entityMap.get(type).add(entity);

        }finally {
            addComponentLock.unlock();
            printStatus();
        }
    }

    public void addBullet(Type type, Entity entity){
        addComponentLock.lock();
        try {
            Entity bullet = spiLocator.getBullet().create(entity.getPosition(),entity.getRadians());
            entityMap.get(type).add(bullet);


            Layers layer = spiLocator.getiDrawableMap().get(type).getLayer();
            addDrawables(spiLocator.getiDrawableMap().get(type),layer);
        } finally {
            addComponentLock.unlock();
            printStatus();
        }
    }

    public void addMap(){
        mapLock.lock();
        try {
            this.map =  spiLocator.getMap().create(this);
            Layers layer = spiLocator.getiDrawableMap().get(Type.MAP).getLayer();
            addDrawables(spiLocator.getiDrawableMap().get(Type.MAP),layer);
        }finally {
            mapLock.unlock();
        }
    }

    /**
     * @return List of all entities that's inbound for the game.
     */

    public LinkedList<Entity> getEntityList(Type type) {
        newLock.lock();
        try{
            return this.entityMap.get(type);
        } finally {
            newLock.unlock();
        }
    }

    /**
     * @return List of all entities that has a process to run.
     */
    public List<IProcessing> getProcesses() {
        processLock.lock();
        try {
            return processes;
        }finally {
            processLock.unlock();
        }
    }

    /**
     * Works just like {@link GameData#addDrawables(IDrawable, Layers)}. Layers is presumed to be Middleground.
     * @see GameData#addDrawables(IDrawable, Layers)
     */
    public boolean addDrawables(IDrawable draw) {
        return addDrawables(draw,Layers.MIDDLEGROUND);
    }

    /**
     * @param draw implementation of {@link IDrawable} to be inserted in the draw cycle.
     * @param layer which layer it should be drawn on.
     * @return returns true if successful.
     */
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

    /**
     * @param newEntity new implementation of {@link IPlugin} that has to be processed by the GameEngine to become part of the game.
     * @return returns true if successful.
     */
    public boolean addNewEntity(Entity newEntity) {
        newLock.lock();

        try {
            if (this.entityMap.get(newEntity.getType()).add(newEntity)){
                return true;
            }else{
                return false;
            }
        }finally {
            newLock.unlock();
        }
    }

    /**
     * @param process implementation of {@link IProcessing} that's ready to join the gameLoop.
     * @return returns true if successful.
     */
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

    /**
     * @param drawable implementation of {@link IDrawable} that has to be removed.
     * @param layer which layer it resides on.
     * @return returns true if successful.
     */
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

    /**
     * @param process implementation of {@link IProcessing} that has to be removed.
     * @return returns true if successful.
     */
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

    public Dimension getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Dimension screenSize) {
        this.screenSize = screenSize;
    }

    public CommonMap getMap() {
        mapLock.lock();
        try {
            return map;
        }finally {
            mapLock.unlock();
        }
    }

    private void printStatus(){
        System.out.println("--------------------------");
        for (LinkedList<Entity> linkedList : entityMap.values()){
            for(Entity entity : linkedList){
                System.out.println(entity.getType() +": " + entity.getPosition()[0]+","+entity.getPosition()[1]);
            }
        }
        System.out.println("--------------------------");
    }
}
