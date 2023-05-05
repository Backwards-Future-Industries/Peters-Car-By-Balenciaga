package interfaces;

import utilities.Layers;

import java.util.LinkedList;

public interface IGameEngine {

    LinkedList<IDrawable> getDrawables();
    LinkedList<IPlugin> getNewEntities();
    LinkedList<IProcessing> getProcesses();
    boolean addDrawables(IDrawable drawable);
    boolean addDrawables(IDrawable drawable, Layers layer);
    // If a layer is not specified, the addDrawables method will place the input (e.g. a map) to the "MIDDLEGROUND"
    boolean addNewEntities(IPlugin newEntity);
    boolean addProcesses(IProcessing process);
    boolean removeDrawables(IDrawable drawable, Layers layer);
    boolean removeProcesses(IProcessing process);
}
