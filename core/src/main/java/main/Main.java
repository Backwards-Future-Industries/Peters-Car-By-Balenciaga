package main;

import abstractClasses.Entity;
import gameEngine.GameEngine;
import interfaces.IDrawable;
import interfaces.IPlugin;
import interfaces.IProcessing;
import player.PlayerPlugin;
import utilities.SPIlocator;
import map.Map;


import java.io.IOException;
import java.util.Collection;
import java.util.ServiceLoader;



public class Main {

    public static void main(String[] args) {
        GameEngine gm = new GameEngine(60);

    }
}