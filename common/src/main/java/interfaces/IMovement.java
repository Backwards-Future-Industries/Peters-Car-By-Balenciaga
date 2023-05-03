package interfaces;

import abstractClasses.Entity;
import utilities.Inputs;
import utilities.Vector2D;

import java.util.ArrayList;

public interface IMovement {
    int[] defaultMove(ArrayList<Inputs> inputs, Entity entity);
}
