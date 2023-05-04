package interfaces;

import abstractClasses.Entity;
import utilities.GameData;

public interface IPlugin {

    Entity create(GameData gameEngine);
    Entity delete(GameData gameEngine);
}
