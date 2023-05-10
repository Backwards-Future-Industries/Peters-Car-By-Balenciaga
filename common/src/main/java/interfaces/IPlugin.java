package interfaces;

import abstractClasses.Entity;
import utilities.GameData;

import java.io.IOException;

public interface IPlugin {

    Entity create(GameData gameData) throws IOException;
    Entity delete(GameData gameData);

}
