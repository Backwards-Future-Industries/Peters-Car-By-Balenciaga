package interfaces;

import abstractClasses.CommonMap;
import abstractClasses.Entity;
import utilities.GameData;

public interface IMapService {
    CommonMap create(GameData gameData);
    void delete();
}
