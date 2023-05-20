package interfaces;

import utilities.GameData;
import utilities.Inputs;

import java.util.ArrayList;

public interface IProcessing {

    void process(ArrayList<Inputs> inputs, GameData gameData);
}
