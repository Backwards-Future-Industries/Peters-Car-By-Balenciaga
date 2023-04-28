package main;

import gameEngine.GameEngine;
import interfaces.IPlugin;
import player.PlayerPlugin;
import utilities.SPIlocator;


import java.io.IOException;
import java.util.Collection;


public class Main {

    public static void main(String[] args) {
    /*    GameEngine gm = new GameEngine(60);
        PlayerPlugin player;
        Bullet bullet;
        try {
            player = new PlayerPlugin();
            bullet = new Bullet();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gm.addDrawables(player);
        gm.addProcesses(player);


        for (IPlugin iPlugin : Main.getPlugin()){
            System.out.println(iPlugin.create().getAcceleration());
        }

     */
    }

    public static Collection<IPlugin> getPlugin(){
        return SPIlocator.locateAll(IPlugin.class);
    }

}