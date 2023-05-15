package grillBullet;

import abstractClasses.Entity;
import interfaces.IProcessing;
import utilities.GameData;
import utilities.Inputs;
import utilities.Type;

import java.util.ArrayList;

public class BulletControlSystem implements IProcessing {

    @Override
    public void process(ArrayList<Inputs> inputs, GameData gameData) {
        for (Entity bullet : gameData.getEntityList(Type.BULLET)) {
            if (bullet.getType() == Type.BULLET) {
                //for (IMovement iMovement : getIPlugin()) {}}}
            }
        }
    }
}