package main;

import gameEngine.GameEngine;
import utilities.GameData;

import java.awt.*;


public class Main {

    public static void main(String[] args)  {
        GameEngine gm = new GameEngine(60, new GameData(), new Dimension(1280,960));
    }
}