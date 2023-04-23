package main;

import gameEngine.GameEngine;
import player.PlayerPlugin;

import java.io.IOException;

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