package interfaces;

import utilities.Inputs;
import utilities.Vector2D;

import java.util.ArrayList;

public interface IMovement {

     default int[] defaultMove(ArrayList<Inputs> inputs, int[] position) {
         int x = 0;
         int y = 0;
         int[] newPosition = new int[2];

         if(inputs.contains(Inputs.KEY_W)){
             y -= 1;
         }
         if(inputs.contains(Inputs.KEY_A)){
             x -= 1;
         }
         if(inputs.contains(Inputs.KEY_S)){
             y += 1;
         }
         if(inputs.contains(Inputs.KEY_D)){
             x += 1;
         }
         newPosition[0] = position[0] + x;
         newPosition[1] = position[1] + y;

         return newPosition;
    }
}
