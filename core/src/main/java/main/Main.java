package main;

import gameEngine.GameEngine;
import interfaces.IPlugin;
import player.PlayerPlugin;
import utilities.SPIlocator;

import java.io.IOException;
import java.util.Collection;


public class Main {

    public static void main(String[] args) {
        for (IPlugin iPlugin : Main.getPlugin()){
            System.out.println(iPlugin.create().getAcceleration());
        }
    }

    public static Collection<IPlugin> getPlugin(){
        return SPIlocator.locateAll(IPlugin.class);
    }

}