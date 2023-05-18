package main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import gameEngine.GameEngine;
import utilities.GameData;

import java.awt.*;


public class Main {

    public static void main(String[] args)  {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setResizable(true);
        config.setTitle("Nelda");
        config.setWindowedMode(1000, 600);
        config.setInitialVisible(false);
        new Lwjgl3Application(new ApplicationStarter() , config);
    }
}