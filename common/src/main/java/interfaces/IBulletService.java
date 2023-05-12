package interfaces;

import abstractClasses.Entity;

import javax.swing.*;
import java.awt.*;

public interface IBulletService {
    Entity create(Entity entity);

    IDrawable draw (Graphics2D g, JPanel panel);

    void Process(Entity entity);
}
