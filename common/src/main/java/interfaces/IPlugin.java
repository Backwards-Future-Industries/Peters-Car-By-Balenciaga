package interfaces;

import abstractClasses.Entity;
import utilities.GameData;

public interface IPlugin {

    Entity create();
    Entity delete(GameData gameData);

}
