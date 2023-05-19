package interfaces;

import abstractClasses.Entity;
import utilities.GameData;

public interface IBulletService{
    Entity create(Entity entity);

    void delete(GameData gameData);

}
