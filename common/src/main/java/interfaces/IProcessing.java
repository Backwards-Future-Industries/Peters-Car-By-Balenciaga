package interfaces;

import abstractClasses.Entity;
import utilities.GameData;
import utilities.Inputs;

import java.util.ArrayList;

public interface IProcessing {

    public void process(ArrayList<Inputs> inputs, GameData gameData);
}
