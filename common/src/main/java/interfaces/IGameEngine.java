package interfaces;

import utilities.Layers;

import java.util.LinkedList;

public interface IGameEngine {

    LinkedList<IDrawable> getDrawables();
    LinkedList<IPlugin> getNewEntities();
    LinkedList<IProcessing> getProcesses();
    boolean addDrawables(IDrawable drawable);
    boolean addDrawables(IDrawable drawable, Layers layer);
    boolean addNewEntities(IPlugin newEntity);
    boolean addProcesses(IProcessing process);
    boolean removeDrawables(IDrawable drawable, Layers layer);
    boolean removeProcesses(IProcessing process);
}
