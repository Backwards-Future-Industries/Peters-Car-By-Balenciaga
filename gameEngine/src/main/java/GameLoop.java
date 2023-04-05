import interfaces.IPlugin;
import interfaces.IProcessing;

import java.util.LinkedList;

public class GameLoop extends Thread {

    private LinkedList<IPlugin> newEntities;
    private LinkedList<IProcessing>processes;


    public GameLoop(LinkedList<IPlugin> newEntities, LinkedList<IProcessing> processes){
        this.newEntities = newEntities;
        this.processes = processes;
    }


    @Override
    public void run() {
        while (true) {
            for(IPlugin newEntity : newEntities){
                newEntity.create();
            }
            for(IProcessing entity : processes){
                entity.process();
            }
        }
    }

}
