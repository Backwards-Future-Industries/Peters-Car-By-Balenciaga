package interfaces;

import abstractClasses.Entity;
import utilities.GameData;
import utilities.Inputs;

import java.awt.*;
import java.util.ArrayList;

public interface IMovement {
    Point defaultMove(ArrayList<Inputs> inputs, Entity entity, GameData gameData);
}
