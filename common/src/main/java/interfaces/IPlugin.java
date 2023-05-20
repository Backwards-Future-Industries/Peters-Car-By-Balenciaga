package interfaces;

import abstractClasses.Entity;
import utilities.GameData;

public interface IPlugin {

    Entity create();

    void delete(GameData gameData, Entity entity);

}
