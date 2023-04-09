import interfaces.IProcessing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import utilities.Inputs;

public class UserInputs extends KeyAdapter {

    private ArrayList<Inputs> inputs;

    public UserInputs(){
        super();

    }
    @Override
    public void keyPressed(KeyEvent e){

        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_W){
            inputs.add(Inputs.KEY_W);
        }
        if(keyCode == KeyEvent.VK_A){
            inputs.add(Inputs.KEY_A);
        }
        if(keyCode == KeyEvent.VK_S){
            inputs.add(Inputs.KEY_S);
        }
        if(keyCode == KeyEvent.VK_D){
            inputs.add(Inputs.KEY_D);
        }
        if(keyCode == KeyEvent.VK_SPACE){
            inputs.add(Inputs.KEY_SPACE);
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
        if(keyCode == KeyEvent.VK_SPACE){
            inputs.add(Inputs.KEY_SPACE);
        }
    }

    public ArrayList<Inputs> getInputs() {
        return inputs;
    }
}
