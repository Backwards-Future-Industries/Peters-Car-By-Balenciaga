package main;

import gameEngine.GameEngine;
import interfaces.IPlugin;
import player.PlayerPlugin;
import utilities.SPIlocator;

import java.io.IOException;
import java.util.Collection;


public class Main {

    public static void main(String[] args) {
        GameEngine gm = new GameEngine(60);
        PlayerPlugin player;

        try {

            player = new PlayerPlugin();
            mapShapes = new MapShapes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // By adding mapShapes before the player, we can control the players color from the draw-method in class MapShapes

        gm.addDrawables(mapShapes);
        gm.addDrawables(player);
        gm.addProcesses(player);


        for (IPlugin iPlugin : Main.getPlugin()){
            System.out.println(iPlugin.create(gm).getAcceleration());
        }
    }

    public static Collection<IPlugin> getPlugin(){
        return SPIlocator.locateAll(IPlugin.class);
    }

}