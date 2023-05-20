package interfaces;

import utilities.GameData;
import utilities.Layer;

import javax.swing.*;
import java.awt.*;

public interface IDrawable {
    void draw(Graphics2D g, JPanel panel, GameData gameData);

    Layer getLayer();
}
