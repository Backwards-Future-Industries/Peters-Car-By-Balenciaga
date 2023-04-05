import interfaces.IDrawable;

import java.util.LinkedList;

public class DrawLoop extends Thread {
    private double framerate;
    private long lastDraw;
    private LinkedList<IDrawable> drawables;


    public DrawLoop(double framerate, LinkedList<IDrawable> drawables){
        this.framerate = framerate;
        this.drawables = drawables;
        this.lastDraw = 0;
    }


    @Override
    public void run() {
        while (true) {
            while (1.0/(getDeltaTime()*1000.0) <= framerate){
                for (IDrawable entity : drawables) {
                    entity.draw();
                }
                lastDraw = System.currentTimeMillis();
            }
        }
    }

    private double getDeltaTime(){
        return System.currentTimeMillis() - lastDraw;
    }
}
