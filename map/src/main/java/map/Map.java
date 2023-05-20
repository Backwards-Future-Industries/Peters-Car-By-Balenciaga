package map;

import abstractClasses.CommonMap;
import interfaces.IDrawable;
import interfaces.IMapService;
import utilities.GameData;
import utilities.Layer;
import utilities.TileType;
import utilities.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Map extends CommonMap implements IDrawable, IMapService {

    private Bitmap bitmap;
    private Tile grass;
    private Tile earth;
    private Tile obstacle;
    private Tile road;
    private Tile roadLineUp;
    private Tile roadLineSide;
    private Tile stLeft;
    private Tile stRight;
    private Tile parking;
    private BufferedImage bufferedImage;

    public Map() {
    }

    public Map(GameData gameData) {
        this.bitmap = new Bitmap();
        this.grass = new Tile(TileType.GRASS);
        this.earth = new Tile(TileType.EARTH);
        this.obstacle = new Tile(TileType.OBSTACLE);
        this.road = new Tile(TileType.ROAD);
        this.roadLineUp = new Tile(TileType.ROADLINEUP);
        this.roadLineSide = new Tile(TileType.ROADLINESIDE);
        this.stLeft = new Tile(TileType.STLEFT);
        this.stRight = new Tile(TileType.STRIGHT);
        this.parking = new Tile(TileType.PARKING);
        combinedTiles(gameData);
        this.setAiMap(bitmap.getAiMap());
    }

    @Override
    public CommonMap create(GameData gameData) {
        Map map = new Map(gameData);
        map.setSprite(map.bufferedImage, new double[]{1, 1});
        map.setPosition(new Point(0, 0));
        return map;
    }

    @Override
    public void delete() {

    }

    // combinedTiles:
    // Combines the buffered images into one image, which is then drawn in the draw-method
    // This makes the draw method draw the map once instead of 60 times as it otherwise would do.
    // It also adds the mapImages (the graphic for the tiles) as Shapes to an arraylist -
    // so the tiles/shapes can be defined as obstacles, roads ect. and collision control can be performed on the map.
    private void combinedTiles(GameData gameData) {
        bufferedImage = new BufferedImage(bitmap.getMap().length * 16, bitmap.getMap()[0].length * 16,
                BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g = bufferedImage.createGraphics();
        TileType[][] map = bitmap.getMap();

        Point position = new Point(0, 0);

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                TileType tileType = map[y][x];
                switch (tileType) {
                    case EARTH -> g.drawImage(earth.getSprite().getImage(), position.x, position.y, null);

                    case GRASS -> g.drawImage(grass.getSprite().getImage(), position.x, position.y, null);

                    case OBSTACLE -> g.drawImage(obstacle.getSprite().getImage(), position.x, position.y, null);

                    case ROAD -> g.drawImage(road.getSprite().getImage(), position.x, position.y, null);

                    case ROADLINEUP -> g.drawImage(roadLineUp.getSprite().getImage(), position.x, position.y, null);

                    case ROADLINESIDE -> g.drawImage(roadLineSide.getSprite().getImage(), position.x, position.y, null);

                    case STLEFT -> g.drawImage(stLeft.getSprite().getImage(), position.x, position.y, null);

                    case STRIGHT -> g.drawImage(stRight.getSprite().getImage(), position.x, position.y, null);

                    case PARKING -> g.drawImage(parking.getSprite().getImage(), position.x, position.y, null);
                }
                if (tileType == TileType.OBSTACLE) {
                    g.drawImage(obstacle.getSprite().getImage(), position.x, position.y, null);
                    Tile tile = new Tile(TileType.OBSTACLE);
                    tile.setPosition(position);
                    gameData.addNewEntity(tile);
                }
                position.y += 16;
            }
            position.x += 16;
            position.y = 0;
        }
    }

    @Override
    public void draw(Graphics2D g, JPanel panel, GameData gameData) {
        CommonMap map = gameData.getMap();
        g.drawImage(map.getSprite().getImage(), 0, 0, panel);
    }

    @Override
    public Layer getLayer() {
        return Layer.BACKGROUND;
    }

    @Override
    public String toString() {
        return Type.MAP.toString();
    }
}
