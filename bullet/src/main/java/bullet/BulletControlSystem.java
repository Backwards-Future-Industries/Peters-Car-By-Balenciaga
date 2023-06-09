package bullet;

import abstractClasses.Entity;
import interfaces.IProcessing;
import utilities.GameData;
import utilities.Inputs;
import utilities.SPILocator;
import utilities.Type;

import java.util.ArrayList;
import java.util.List;

import static utilities.Inputs.KEY_W;

public class BulletControlSystem implements IProcessing {

    @Override
    public void process(ArrayList<Inputs> inputs, GameData gameData) {
        for (Entity bullet : gameData.getEntityList(Type.BULLET)) {
            if (bullet.getHealth() <= 0) {
                SPILocator.getSpIlocator().getBullet().delete(gameData, bullet);
            }
            SPILocator.getSpIlocator().getMovement().defaultMove(new ArrayList<>(List.of(KEY_W)), bullet, gameData);

        }

    }

    @Override
    public String toString() {
        return Type.BULLET.toString();
    }
}
