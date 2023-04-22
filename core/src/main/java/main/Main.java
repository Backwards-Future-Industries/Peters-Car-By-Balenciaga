package main;

import abstractClasses.Entity;
import gameEngine.GameEngine;
import interfaces.IPlugin;
import player.PlayerPlugin;
import utilities.SPILocater;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ServiceLoader;

public class Main {

    private ArrayList<Entity> arrayList = new ArrayList<>();
    SPILocater spiLocater = new SPILocater();

    public static void main(String[] args) {



        ArrayList<Entity> arrayList = new ArrayList<>();

        /*GameEngine gm = new GameEngine(60);
        PlayerPlugin player;
        try {
            player = new PlayerPlugin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gm.addDrawables(player);
        gm.addProcesses(player);

         */
        ServiceLoader<Entity> serviceLoader = ServiceLoader.load(Entity.class);
        SPILocater spiLocater = new SPILocater();



        for (Entity iPlugin : serviceLoader){
            System.out.println(iPlugin.getHealth());

        }




    }


    public ArrayList<Entity> arrayList(Entity entity){
        arrayList = spiLocater.localteAll(entity.getClass())
    }

}