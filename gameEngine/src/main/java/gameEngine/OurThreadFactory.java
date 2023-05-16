package gameEngine;

import java.util.concurrent.ThreadFactory;

public class OurThreadFactory implements ThreadFactory {

    private String name;

    public OurThreadFactory(String name){
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, name);
    }
}
