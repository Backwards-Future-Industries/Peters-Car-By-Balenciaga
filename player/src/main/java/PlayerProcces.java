import interfaces.IMovement;
import interfaces.IProcessing;
import utilities.Inputs;
import utilities.Vector2D;

import java.util.ArrayList;

public class PlayerProcces implements IProcessing, IMovement {


    @Override
    public Vector2D defaultMove() throws NoSuchMethodException {
        return IMovement.super.defaultMove();
    }

    @Override
    public void process(ArrayList<Inputs> inputs) {

    }
}
