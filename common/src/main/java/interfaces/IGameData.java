package interfaces;

import abstractClasses.Entity;
import utilities.Layers;

import java.util.LinkedList;

public interface IGameData {

    LinkedList<IDrawable> getDrawables();
    LinkedList<Entity> getNewEntities();
    LinkedList<IProcessing> getProcesses();
    boolean addDrawables(IDrawable drawable);
    boolean addDrawables(IDrawable drawable, Layers layer);
    // If a layer is not specified, the addDrawables method will place the input (e.g. a map) to the "MIDDLEGROUND"
    boolean addNewEntities(Entity newEntity);
    boolean addProcesses(IProcessing process);
    boolean removeDrawables(IDrawable drawable, Layers layer);
    boolean removeProcesses(IProcessing process);
}
