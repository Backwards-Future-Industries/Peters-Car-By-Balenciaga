package map;

import abstractClasses.CommonMap;
import interfaces.IDrawable;
import interfaces.IMapService;
import utilities.GameData;
import utilities.Layers;
import utilities.TileType;
import utilities.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.Map.Entry;
import java.util.List;


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
        map.setPosition(new int[]{1, 1});
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

        int[] position = new int[]{0, 0};
        LinkedList<Tile> obstacleList = new LinkedList<>();

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                TileType tileType = map[y][x];
                switch (tileType) {
                    case EARTH -> g.drawImage(earth.getSprite().getImage(), position[0], position[1], null);

                    case GRASS -> g.drawImage(grass.getSprite().getImage(), position[0], position[1], null);

                    case OBSTACLE -> g.drawImage(obstacle.getSprite().getImage(), position[0], position[1], null);

                    case ROAD -> g.drawImage(road.getSprite().getImage(), position[0], position[1], null);

                    case ROADLINEUP -> g.drawImage(roadLineUp.getSprite().getImage(), position[0], position[1], null);

                    case ROADLINESIDE -> g.drawImage(roadLineSide.getSprite().getImage(), position[0], position[1], null);

                    case STLEFT -> g.drawImage(stLeft.getSprite().getImage(), position[0], position[1], null);

                    case STRIGHT -> g.drawImage(stRight.getSprite().getImage(), position[0], position[1], null);

                    case PARKING -> g.drawImage(parking.getSprite().getImage(), position[0], position[1], null);
                }
                if (tileType == TileType.OBSTACLE) {
                    g.drawImage(obstacle.getSprite().getImage(), position[0], position[1], null);
                    Tile tile = new Tile(TileType.OBSTACLE);
                    tile.setPosition(position);
                    obstacleList.add(tile);
                }
                position = new int[]{position[0], position[1] + 16};
            }
            position = new int[]{position[0] + 16, 0};
        }

        obstacleList.sort(new TileComparatorY());
        System.out.println(obstacleList);
        TreeMap<Integer, LinkedList<Tile>> mapX = new TreeMap<>();

        for(Tile tile : obstacleList){
            int[] pos = tile.getPosition();
            if (!mapX.containsKey(pos[1])){
                mapX.put(pos[1],new LinkedList<>());
            }
            mapX.get(tile.getPosition()[1]).add(tile);
        }

        LinkedList<Tile> testEntities = new LinkedList<>();

        TreeMap<Integer,Boolean> test = new TreeMap<>();
        TreeMap<Integer,LinkedList<Tile>> fuckedObstacles = new TreeMap<>();
        for (var entry : mapX.entrySet()){
            if(validateX(entry.getValue())){
                int[] startPos = entry.getValue().getFirst().getPosition();
                Tile tile = new Tile(TileType.OBSTACLE);
                tile.setPosition(startPos);
                tile.setSprite(Map.class.getResource("/mapImages/obstacle2.png"),new double[]{entry.getValue().size(),1});
                testEntities.add(tile);
                test.put(entry.getKey(),true);
                obstacleList.removeAll(entry.getValue());
            }else {
                test.put(entry.getKey(),false);
                fuckedObstacles.put(entry.getKey(),entry.getValue());
            }
        }

        LinkedList<Tile> newTestEntities = new LinkedList<>();
        for(int i = 0; i<testEntities.size()-1; i++){
            if(testEntities.get(i).getPosition()[1]+16 == testEntities.get(i+1).getPosition()[1]){
                int[] startPos = testEntities.get(i).getPosition();
                Tile tile = new Tile(TileType.OBSTACLE);
                tile.setPosition(startPos);
                tile.setSprite(testEntities.get(i).getSprite().getImage(), new double[]{1,2});
                newTestEntities.add(tile);
            }
        }

        obstacleList.sort(new TileComparatorX());

        LinkedList<Tile> fuckedTile = new LinkedList<>();
        boolean firstTime = true;
        int pollCounter = 1;
        int[] startPos = new int[]{0,0};
        for(int i = 0; i<obstacleList.size()-1; i++){
            if(firstTime){
                firstTime = false;
                startPos = obstacleList.get(i).getPosition();
            }
            if(obstacleList.get(i).getPosition()[1]+16 == obstacleList.get(i+1).getPosition()[1]){
                pollCounter += 1;
            }else {
                Tile newTile = new Tile(TileType.OBSTACLE);
                newTile.setPosition(startPos);
                newTile.setSprite(newTile.getSprite().getImage(), new double[]{1,pollCounter});
                fuckedTile.add(newTile);
                pollCounter = 1;
                firstTime = true;
            }
        }

        for(int i = 0; i<fuckedTile.size()-1; i++){
            if(fuckedTile.get(i).getPosition()[0]+16 == fuckedTile.get(i+1).getPosition()[0]){
                int[] startPos2 = fuckedTile.get(i).getPosition();
                Tile tile2 = new Tile(TileType.OBSTACLE);
                tile2.setPosition(startPos2);
                tile2.setSprite(fuckedTile.get(i).getSprite().getImage(), new double[]{2,1});
                newTestEntities.add(tile2);
            }
        }
    }

    private boolean validateX(LinkedList<Tile> tiles){
        for(int i = 0; i<tiles.size()-1;i++){
            if(tiles.get(i).getPosition()[0]+16 != tiles.get(i+1).getPosition()[0]){
                return false;
            }
        }
        return true;
    }

    @Override
    public void draw(Graphics2D g, JPanel panel, GameData gameData) {
        CommonMap map = gameData.getMap();
        g.drawImage(map.getSprite().getImage(), 0, 0, panel);
    }

    @Override
    public Layers getLayer() {
        return Layers.BACKGROUND;
    }

    @Override
    public String toString() {
        return Type.MAP.toString();
    }
}
