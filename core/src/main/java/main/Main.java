package main;

import abstractClasses.Entity;
import gameEngine.GameEngine;
import interfaces.IPlugin;
import player.PlayerPlugin;
import utilities.SPILocater;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ServiceLoader;

public class Main {

    public static void main(String[] args) {
        GameEngine gm = new GameEngine(60);
        PlayerPlugin player;
        try {
            player = new PlayerPlugin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gm.addDrawables(player);
        gm.addProcesses(player);
    }
}