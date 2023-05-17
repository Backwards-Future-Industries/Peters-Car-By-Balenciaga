package map;

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
        URL url = Bitmap.class.getResource("/bitmaps/bitMapMaze.png");
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
                TileType tileType = findTile(blue,green,red);
                map[x][y] = tileType;
                aiMap[y][x] = tileTypeToInt(tileType);
            }
        }

    }

    private TileType findTile(int blue, int green, int red) {
        Color color = Color.cyan;
        if (blue > 250 && green > 250 && red > 250) {
            color = Color.black;
        } else if (blue < 5 && green < 5 && red < 5) {
            color = Color.white;
        } else if (blue < 5 && green < 5 && red > 250) {
            color = Color.red;
        }
        return getColorTileType(color);
    }

    private TileType getColorTileType(Color color) {
        if (TileType.GRASS.getColor() == color) {
            return TileType.GRASS;
        } else if (TileType.EARTH.getColor() == color) {
            return TileType.EARTH;
        } else if (TileType.OBSTACLE.getColor() == color) {
            return TileType.OBSTACLE;
        }
        return TileType.BLANK;
    }

    private int tileTypeToInt(TileType tileType) {
        if (tileType == TileType.GRASS) {
            return 0;
        } else if (tileType == TileType.EARTH) {
            return 1;
        } else if (tileType == TileType.OBSTACLE) {
            return 2;
        }
        return -1;
    }

    public TileType[][] getMap() {
        return map;
    }
}
