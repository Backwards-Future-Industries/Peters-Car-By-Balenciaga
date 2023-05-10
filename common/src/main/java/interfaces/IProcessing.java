package interfaces;

import abstractClasses.Entity;
import utilities.Inputs;

import java.util.ArrayList;

public interface IProcessing {

    public void process(ArrayList<Inputs> inputs, Entity entity);
}
