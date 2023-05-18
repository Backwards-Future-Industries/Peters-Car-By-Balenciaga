package map;

import utilities.TileType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class Bitmap {

    private TileType[][] map;
    private BufferedImage bitmap;

    private int[][] aiMap;

    public Bitmap(){
        URL url = Bitmap.class.getResource("/bitmaps/bitMap7.0.png");
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
                int blue = color & 0xff;
                int green = (color & 0xff00) >> 8;
                int red = (color & 0xff0000) >> 16;
                TileType tileType = findTile(red, green, blue);
                map[x][y] = tileType;
                aiMap[y][x] = tileTypeToInt(tileType);
            }
        }

    }

    private TileType findTile(int red, int green, int blue){
        Color color = Color.CYAN;
        if(blue == 0 && green == 255 && red == 0){
            color = Color.GREEN;
        }
        if(red == 255 && green == 255 && blue == 255){
            color = Color.WHITE;
        }
        if (red == 255 && green == 0 && blue == 0){
            color = Color.RED;
        }
        if (red == 128 && green == 128 && blue == 128){
            color = Color.GRAY;
        }
        if (red == 0 && green == 0 && blue == 255) {
            color = Color.BLUE;
        }
        if (red == 255 && green == 0 && blue == 255) {
            color = Color.MAGENTA;
        }
        if (red == 255 && green == 175 && blue == 175) {
            color = Color.PINK;
        }
        if (red == 255 && green == 255 && blue == 0) {
            color = Color.YELLOW;
        }
        return getColorTileType(color);
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