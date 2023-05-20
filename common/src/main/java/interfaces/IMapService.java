package interfaces;

import abstractClasses.CommonMap;
import utilities.GameData;

public interface IMapService {
    CommonMap create(GameData gameData);

    void delete();
}
