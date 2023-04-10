package main;
import gameEngine.GameEngine;
import interfaces.IDrawable;
import player.PlayerPlugin;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        GameEngine gm = new GameEngine(60);
        try {
            gm.addDrawables((IDrawable) new PlayerPlugin());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}