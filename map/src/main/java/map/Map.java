package map;

import abstractClasses.Entity;
import interfaces.IDrawable;
import interfaces.IGameEngine;
import interfaces.IPlugin;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Map extends Entity implements IDrawable, IPlugin {

    private Bitmap bitmap;
    private Tile grass;
    private Tile earth;
    private Tile wallTest;

    private void combinedTiles() {

    }


    public Map() throws IOException {
        this.bitmap = new Bitmap();
        this.grass = new Tile(TileType.GRASS);
        this.earth = new Tile(TileType.EARTH);
        this.wallTest = new Tile(TileType.WALLTEST);

    }

    @Override
    public Entity create(IGameEngine gameEngine) {
        return null;
    }

    @Override
    public Entity delete(IGameEngine gameEngine) {
        return null;
    }




    @Override
    public void draw(Graphics2D g, JPanel panel) {
        // Prøv og merge bufferedImages ind til et enkelt image
        // Ved at merge bufferedImages til et enkelt image, vil køretiden blive bedre
        // Se bufferedImage-doku

        //Lav metode der rykker bufferedImage sammen - to buffered images der rykkes sammen.

        TileType[][] map = bitmap.getMap();

        int[] position = new int[]{0,0};

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if(map[x][y] == TileType.EARTH){
                    g.drawImage(earth.getSprite().getImage(),position[0],position[1],panel);
                }
                if(map[x][y] == TileType.GRASS){
                    g.drawImage(grass.getSprite().getImage(),position[0],position[1],panel);
                }
                if(map[x][y] == TileType.WALLTEST){
                    g.drawImage(wallTest.getSprite().getImage(),position[0],position[1],panel);
                }
                position = new int[]{position[0]+16,position[1]};
            }
            position = new int[]{0,position[1]+16};
        }
    }
}
