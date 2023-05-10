package interfaces;

import abstractClasses.Entity;

public interface IPlugin {

    Entity create(IGameEngine gameEngine);
    Entity delete(IGameEngine gameEngine);

}
