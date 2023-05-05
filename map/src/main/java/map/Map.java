package map;

import abstractClasses.Entity;
import interfaces.IDrawable;
import interfaces.IGameEngine;
import interfaces.IPlugin;
import utilities.Shapes;
import utilities.Types;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Map extends Entity implements IDrawable, IPlugin {

    private Bitmap bitmap;
    private Tile grass;
    private Tile earth;
    private Tile obstacle;

    private ArrayList <Shapes> shapesArray;

    private void combinedTiles() {

        BufferedImage bufferedImage = new BufferedImage(bitmap.getMap().length*16,
                bitmap.getMap()[0].length*16,
                BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g = bufferedImage.createGraphics();
        TileType[][] map = bitmap.getMap();

        int[] position = new int[]{0,0};

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                TileType tileType = map[x][y];
                if(tileType == TileType.EARTH){
                    g.drawImage(earth.getSprite().getImage(),position[0],position[1],null);
                }
                if(tileType == TileType.GRASS){
                    g.drawImage(grass.getSprite().getImage(),position[0],position[1],null);
                }
                if(tileType == TileType.OBSTACLE){
                    g.drawImage(obstacle.getSprite().getImage(),position[0],position[1],null);
                }
                position = new int[]{position[0]+16,position[1]};
                if (tileType == TileType.OBSTACLE) {
                    shapesArray.add(new Shapes(16,16,position, Types.OBSTACLE));
                }
                else {
                    shapesArray.add(new Shapes(16,16,position, Types.UNDEFINED));
                }
            }
            position = new int[]{0,position[1]+16};
        }
        this.setSprite(bufferedImage,new double[]{1,1});
        this.setShape((shapesArray.toArray(this.getShape())));

        System.out.println("LOLOL");
    }



    public Map() throws IOException {
        this.bitmap = new Bitmap();
        this.grass = new Tile(TileType.GRASS);
        this.earth = new Tile(TileType.EARTH);
        this.obstacle = new Tile(TileType.OBSTACLE);
        this.shapesArray = new ArrayList<Shapes>();
        combinedTiles();
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
        g.drawImage(getSprite().getImage(),0,0,panel);

    }
}
