package interfaces;

import utilities.GameData;

import javax.swing.*;
import java.awt.*;

public interface IDrawable {
    void draw(Graphics2D g, JPanel panel, GameData gameData);
}
