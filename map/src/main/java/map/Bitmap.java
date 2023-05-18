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
        URL url = Bitmap.class.getResource("/bitmaps/bitMap1.0.png");
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
                TileType TileType = findTile(blue,green,red);
                map[x][y] = TileType;
                aiMap[y][x] = findTileInt(blue,green,red);
            }
        }

    }

    private TileType findTile(int blue, int green, int red){
        Color color = Color.CYAN;

        if(blue == 0 && green == 255 && red == 0){
            color = Color.GREEN;
        }

        if(blue == 255 && green == 255 && red == 255){
            color = Color.WHITE;
        }

        if (blue == 0 && green == 0 && red == 255){
            color = Color.RED;
        }

        if (blue == 128 && green == 128 && red == 128){
            color = Color.GRAY;
        }

        if(TileType.GRASS.getColor() == color){
            return TileType.GRASS;
        }
        if(TileType.EARTH.getColor() == color){
            return TileType.EARTH;
        }
        if (TileType.OBSTACLE.getColor() == color){
            return TileType.OBSTACLE;
        }

        if (TileType.ROAD.getColor() == color) {
            return TileType.ROAD;
        }

        return TileType.BLANK;
    }

    private int findTileInt(int blue, int green, int red){
        Color color = Color.CYAN;

        if(blue == 0 && green == 0 && red == 0){
            color = Color.BLACK;
        }

        if(blue < 5 && green < 5 && red < 5){
            color = Color.WHITE;
        }

        if (blue < 5 && green < 5 && red > 250){
            color = Color.RED;
        }

        if (blue < 128 && green < 128 && red < 128){
            color = Color.GRAY;
        }

        if(TileType.GRASS.getColor() == color){
            return 0;
        }
        if(TileType.EARTH.getColor() == color){
            return 1;
        }
        if (TileType.OBSTACLE.getColor() == color){
            return 2;
        }
        if (TileType.ROAD.getColor() == color){
            return 3;
        }

        return -1;
    }

    public TileType[][] getMap() {
        return map;
    }
}
