package interfaces;

import abstractClasses.Entity;
import utilities.GameData;

import javax.swing.*;
import java.awt.*;

public interface IBulletService {
    Entity create(Entity entity);

    void delete(GameData gameData);
}
