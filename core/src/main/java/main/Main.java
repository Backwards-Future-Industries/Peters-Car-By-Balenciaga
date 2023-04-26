package main;
import gameEngine.GameEngine;
import interfaces.IDrawable;
import player.PlayerPlugin;
import weapons.Bullet;
import weapons.Grill;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        GameEngine gm = new GameEngine(60);
        PlayerPlugin player;
        Bullet bullet;
        Grill grill;
        try {
            player = new PlayerPlugin();
            bullet = new Bullet();
            grill = new Grill(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //gm.addDrawables(player);
        //gm.addProcesses(player);

        gm.addDrawables(bullet);
        gm.addProcesses(bullet);

        gm.addDrawables(grill);
        gm.addProcesses(grill);
    }
}