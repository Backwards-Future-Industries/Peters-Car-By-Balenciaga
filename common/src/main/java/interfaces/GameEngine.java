package interfaces;

import java.util.LinkedList;

public interface GameEngine {

    LinkedList<IDrawable> getDrawables();
    LinkedList<IPlugin> getNewEntities();
    LinkedList<IProcessing> getProcesses();
    boolean addDrawables(IDrawable drawable);
    boolean addNewEntities(IPlugin newEntity);
    boolean addProcesses(IProcessing process);
    boolean removeDrawables(IDrawable drawable);
    boolean removeProcesses(IProcessing process);
    long getDeltaDrawTime();
    long getDeltaProcessTime();
}
