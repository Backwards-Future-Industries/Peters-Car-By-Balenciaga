package main;

import com.badlogic.gdx.ApplicationListener;
import gameEngine.GameEngine;
import utilities.GameData;
import utilities.SPIlocator;

public class ApplicationStarter implements ApplicationListener {
    @Override
    public void create() {
            new GameEngine(60, new GameData());


    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
