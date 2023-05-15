package interfaces;

import abstractClasses.Entity;
import utilities.GameData;

public interface IBulletService extends IPlugin{
    Entity create(int[] position, double radians);

    void delete(GameData gameData);

}
