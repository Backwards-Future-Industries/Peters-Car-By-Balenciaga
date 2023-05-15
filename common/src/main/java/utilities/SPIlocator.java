package utilities;

import interfaces.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ServiceLoader;


public class SPIlocator {


    private static SPIlocator spIlocator;
    private HashMap<Type,IPlugin> pluginMap;

    private HashMap<Type, IProcessing> processingMap;

    private HashMap<Type, IDrawable> iDrawableMap;

    private IMovement movement;

    private IBulletService bullet;


    private SPIlocator(){
        makeList();
    }

    public static SPIlocator getSpIlocator(){
        if (spIlocator == null){
            spIlocator = new SPIlocator();
        }
        return spIlocator;
    }

    private void makeList(){
        for (IPlugin iPlugin : loadPlugins()){
            for (Type t : Type.values()){
                if (iPlugin.toString().equals(t.toString())){
                    pluginMap.put(t,iPlugin);
                }
            }
        }

        for (IDrawable iDrawable : loadDrawables()){
            for (Type t : Type.values()){
                if (iDrawable.toString().equals(t.toString())){
                    iDrawableMap.put(t,iDrawable);
                }
            }
        }

        for (IProcessing iProcessing : loadProcesses()){
            for (Type t : Type.values()){
                if (iProcessing.toString().equals(t.toString())){
                    processingMap.put(t,iProcessing);
                }
            }
        }

        loadMovement();

        loadBullet();
    }

    private Collection<IPlugin> loadPlugins(){
        ServiceLoader<IPlugin> serviceLoader = ServiceLoader.load(IPlugin.class);

        LinkedList<IPlugin> list = new LinkedList<>();

        for (IPlugin instance : serviceLoader){
            list.add(instance);
        }

        return list;
    }

    private Collection<IDrawable> loadDrawables(){
        ServiceLoader<IDrawable> serviceLoader = ServiceLoader.load(IDrawable.class);

        LinkedList<IDrawable> list = new LinkedList<>();

        for (IDrawable instance : serviceLoader){
            list.add(instance);
        }

        return list;
    }

    private Collection<IProcessing> loadProcesses(){
        ServiceLoader<IProcessing> serviceLoader = ServiceLoader.load(IProcessing.class);

        LinkedList<IProcessing> list = new LinkedList<>();

        for (IProcessing instance : serviceLoader){
            list.add(instance);
        }

        return list;
    }

    private void loadMovement(){
        ServiceLoader<IMovement> serviceLoader = ServiceLoader.load(IMovement.class);

        for(IMovement instance : serviceLoader){
            movement = instance;
        }
    }

    private void loadBullet(){
        ServiceLoader<IBulletService> serviceLoader = ServiceLoader.load(IBulletService.class);

        for(IBulletService instance : serviceLoader){
            bullet = instance;
        }
    }


    public HashMap<Type, IDrawable> getiDrawableMap() {
        return iDrawableMap;
    }

    public HashMap<Type, IPlugin> getPluginMap() {
        return pluginMap;
    }

    public HashMap<Type, IProcessing> getProcessingMap() {
        return processingMap;
    }

    public IBulletService getBullet() {
        return bullet;
    }

    public IMovement getMovement() {
        return movement;
    }
}

