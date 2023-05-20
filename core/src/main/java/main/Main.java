package main;

import gameEngine.GameEngine;
import utilities.GameData;

import java.awt.*;
import java.util.UUID;


public class Main {

    public static void main(String[] args)  {
        new GameEngine(60, new GameData(),new Dimension(1280,960));
    }
}