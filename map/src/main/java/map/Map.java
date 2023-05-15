package map;

import abstractClasses.Entity;
import interfaces.IDrawable;
import interfaces.IPlugin;
import utilities.GameData;
import utilities.Layers;
import utilities.Shapes;
import utilities.Type;

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

    // combinedTiles:
    // Combines the buffered images into one image, which is then drawn in the draw-method
    // This makes the draw method draw the map once instead of 60 times as it otherwise would do.
    // It also adds the mapImages (the graphic for the tiles) as Shapes to an arraylist -
    // so the tiles/shapes can be defined as obstacles, roads ect. and collision control can be performed on the map.
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
                    shapesArray.add(new Shapes(16,16,position, Type.OBSTACLE));
                }
                else {
                    shapesArray.add(new Shapes(16,16,position, Type.UNDEFINED));
                }
            }
            position = new int[]{0,position[1]+16};
        }
        this.setSprite(bufferedImage,new double[]{1,1});
        this.setShape((shapesArray.toArray(this.getShape())));
    }



    public Map() throws IOException {
        setType(Type.UNDEFINED);
        this.bitmap = new Bitmap();
        this.grass = new Tile(TileType.GRASS);
        this.earth = new Tile(TileType.EARTH);
        this.obstacle = new Tile(TileType.OBSTACLE);
        this.shapesArray = new ArrayList<Shapes>();
        combinedTiles();
    }

    @Override
    public void draw(Graphics2D g, JPanel panel, GameData gameData) {

        g.drawImage(getSprite().getImage(),0,0,panel);

    }

    @Override
    public Layers getLayer() {
        return Layers.BACKGROUND;
    }

    @Override
    public Entity create(GameData gameData){
        Entity newMap = null;
        try {
            newMap = new Map();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return newMap;
    }

    @Override
    public Entity delete(GameData gameData) {
        return null;
    }
}
