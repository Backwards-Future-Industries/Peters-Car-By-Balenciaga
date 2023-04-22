package interfaces;

import abstractClasses.Entity;

public interface IPlugin {

    Entity create(GameEngine gameEngine);
    Entity delete(GameEngine gameEngine);

}
