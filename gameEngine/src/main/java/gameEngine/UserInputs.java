package gameEngine;

import utilities.Inputs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class UserInputs extends KeyAdapter {

    private ArrayList<Inputs> inputs;

    public UserInputs(){
        super();
        this.inputs = new ArrayList<Inputs>();
    }
    @Override
    public void keyPressed(KeyEvent e){

        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_W && !inputs.contains(Inputs.KEY_W)){
            inputs.add(Inputs.KEY_W);
        }
        if(keyCode == KeyEvent.VK_A && !inputs.contains(Inputs.KEY_A)){
            inputs.add(Inputs.KEY_A);
        }
        if(keyCode == KeyEvent.VK_S && !inputs.contains(Inputs.KEY_S)){
            inputs.add(Inputs.KEY_S);
        }
        if(keyCode == KeyEvent.VK_D && !inputs.contains(Inputs.KEY_D)){
            inputs.add(Inputs.KEY_D);
        }
        if(keyCode == KeyEvent.VK_C && !inputs.contains(Inputs.KEY_C)){
            inputs.add(Inputs.KEY_C);
        }
        if(keyCode == KeyEvent.VK_SPACE && !inputs.contains(Inputs.KEY_SPACE)){
            inputs.add(Inputs.KEY_SPACE);
        }
        if(keyCode == KeyEvent.VK_ESCAPE && !inputs.contains(Inputs.KEY_ESC)){
            inputs.add(Inputs.KEY_ESC);
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_W){
            inputs.remove(Inputs.KEY_W);
        }
        if(keyCode == KeyEvent.VK_A){
            inputs.remove(Inputs.KEY_A);
        }
        if(keyCode == KeyEvent.VK_S){
            inputs.remove(Inputs.KEY_S);
        }
        if(keyCode == KeyEvent.VK_D){
            inputs.remove(Inputs.KEY_D);
        }
        if(keyCode == KeyEvent.VK_C){
            inputs.remove(Inputs.KEY_C);
        }
        if(keyCode == KeyEvent.VK_SPACE){
            inputs.remove(Inputs.KEY_SPACE);
        }
        if(keyCode == KeyEvent.VK_ESCAPE){
            inputs.remove(Inputs.KEY_ESC);
        }
    }

    public ArrayList<Inputs> getInputs() {
        return inputs;
    }
}
