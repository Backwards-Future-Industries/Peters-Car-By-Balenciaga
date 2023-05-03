package main;

import gameEngine.GameEngine;
import interfaces.IPlugin;
import player.PlayerPlugin;
import utilities.SPIlocator;
import map.Map;


import java.io.IOException;
import java.util.Collection;



public class Main {

    public static void main(String[] args) {
        GameEngine gm = new GameEngine(60);
        Map map;
        PlayerPlugin player;

        try {

            player = new PlayerPlugin();
            map = new Map();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        gm.addDrawables(map);
        gm.addDrawables(player);
        gm.addProcesses(player);

//        for (IPlugin iPlugin : Main.getPlugin()){
//            System.out.println(iPlugin.create(gm).getAcceleration());
//        }

    }

    public static Collection<IPlugin> getPlugin(){
        return SPIlocator.locateAll(IPlugin.class);

    }

}