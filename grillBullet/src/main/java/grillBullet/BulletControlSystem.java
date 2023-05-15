package grillBullet;

import abstractClasses.Entity;
import interfaces.IMovement;
import interfaces.IProcessing;
import utilities.GameData;
import utilities.Inputs;
import utilities.SPIlocator;
import utilities.Types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static utilities.Inputs.KEY_W;

public class BulletControlSystem implements IProcessing {

    @Override
    public void process(ArrayList<Inputs> inputs, GameData gameData) {
        for (Entity bullet : gameData.getEntityMap(Types.BULLET)) {
            if (bullet.getTypes() == Types.BULLET) {
                for (IMovement iMovement : getIPlugin()) {}}}

}