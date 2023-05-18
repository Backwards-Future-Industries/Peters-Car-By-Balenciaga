package map;

import utilities.TileType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Bitmap {

    private TileType[][] map;
    private BufferedImage bitmap;
    private int[][] aiMap;
    private Color[] arrayOfColors = {Color.GREEN, Color.WHITE, Color.RED, Color.GRAY, Color.BLUE, Color.MAGENTA, Color.PINK, Color.YELLOW, Color.CYAN};

    public Bitmap(){
        URL url = Bitmap.class.getResource("/bitmaps/bitMap8.0.png");
        try {
            bitmap = ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.map = new TileType[bitmap.getWidth()][bitmap.getHeight()];
        this.aiMap = new int[bitmap.getHeight()][bitmap.getWidth()];
        loadMap();
    }

    private void loadMap(){
        for (int y = 0; y < bitmap.getHeight(); y++) {
            for (int x = 0; x < bitmap.getWidth(); x++) {
                //bitmask magic by https://stackoverflow.com/questions/25761438/understanding-bufferedimage-getrgb-output-values
                int color = bitmap.getRGB(x, y);
                TileType tileType = findTile(color);
                map[x][y] = tileType;
                aiMap[y][x] = tileTypeToInt(tileType);
            }
        }

    }

    private TileType findTile(int pixelColor){
        for (Color color : arrayOfColors) {
            if (pixelColor == color.getRGB()) {
                return getColorTileType(color);
            }
        }
        return TileType.BLANK;
    }

    private TileType getColorTileType(Color color) {
        if (TileType.GRASS.getColor() == color) {
            return TileType.GRASS;
        }else if (TileType.EARTH.getColor() == color) {
            return TileType.EARTH;
        } else if (TileType.OBSTACLE.getColor() == color) {
            return TileType.OBSTACLE;
        } else if (TileType.ROAD.getColor() == color) {
            return TileType.ROAD;
        } else if (TileType.ROADLINEUP.getColor() == color) {
            return TileType.ROADLINEUP;
        } else if (TileType.ROADLINESIDE.getColor() == color) {
            return TileType.ROADLINESIDE;
        } else if (TileType.STLEFT.getColor() == color) {
            return TileType.STLEFT;
        } else if (TileType.STRIGHT.getColor() == color) {
            return TileType.STRIGHT;
        }
        return TileType.BLANK;
    }

    private int tileTypeToInt(TileType tileType){
        if(tileType == TileType.GRASS){
            return 2;
        }
        if(tileType == TileType.EARTH){
            return 1;
        }
        if (tileType == TileType.OBSTACLE){
            return 3;
        }
        if (tileType == TileType.ROAD) {
            return 0;
        }
        return 0;
    }

    public TileType[][] getMap() {
        return map;
    }

    public int[][] getAiMap() {
        return aiMap;
    }
}