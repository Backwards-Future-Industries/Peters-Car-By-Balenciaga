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
    private Color[] arrayOfColors = {Color.GREEN,
            Color.WHITE,
            Color.RED,
            Color.GRAY,
            Color.BLUE,
            Color.MAGENTA,
            Color.PINK,
            Color.YELLOW,
            Color.ORANGE,
            Color.CYAN};
    private TileType[] arrayOfTileTypes = {TileType.GRASS,
            TileType.EARTH,
            TileType.OBSTACLE,
            TileType.ROAD,
            TileType.ROADLINEUP,
            TileType.ROADLINESIDE,
            TileType.STLEFT,
            TileType.STRIGHT,
            TileType.PARKING,
            TileType.BLANK};

    public Bitmap() {
        URL url = Bitmap.class.getResource("/bitmaps/bitMap12.0.png");
        try {
            bitmap = ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.map = new TileType[bitmap.getWidth()][bitmap.getHeight()];
        this.aiMap = new int[bitmap.getHeight()][bitmap.getWidth()];
        loadMap();
        increaseObstacleSize();
    }

    private void loadMap() {
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

    private TileType findTile(int pixelColor) {
        for (Color color : arrayOfColors) {
            if (pixelColor == color.getRGB()) {
                return getColorTileType(color);
            }
        }
        return TileType.BLANK;
    }

    private TileType getColorTileType(Color color) {
        for (TileType tileType : arrayOfTileTypes) {
            if (tileType.getColor() == color) {
                return tileType;
            }
        }
        return TileType.BLANK;
    }

    private int tileTypeToInt(TileType tileType) {
        return switch (tileType) {
            case GRASS -> 2;
            case EARTH -> 1;
            case OBSTACLE -> 3;
            default -> 0;
        };
    }

    public TileType[][] getMap() {
        return map;
    }

    public void setBitmap(BufferedImage bitmap) {
        this.bitmap = bitmap;
    }

    public BufferedImage getBitmap() {
        return bitmap;
    }

    public int[][] getAiMap() {
        return aiMap;
    }

    private void increaseObstacleSize() {
        for (int i = 0; i < this.aiMap.length; i++) {
            for (int j = 0; j < this.aiMap[1].length; j++) {
                if (i > 0 && this.aiMap[i - 1][j] == 3 && this.aiMap[i][j] != 3) this.aiMap[i][j] = 5;
                if (i < this.aiMap.length - 1 && this.aiMap[i + 1][j] == 3 && this.aiMap[i][j] != 3)
                    this.aiMap[i][j] = 5;
                if (j > 0 && this.aiMap[i][j - 1] == 3 && this.aiMap[i][j] != 3) this.aiMap[i][j] = 5;
                if (j < this.aiMap[1].length - 1 && this.aiMap[i][j + 1] == 3 && this.aiMap[i][j] != 3)
                    this.aiMap[i][j] = 5;
                if (i > 0 && j > 0 && this.aiMap[i - 1][j - 1] == 3 && this.aiMap[i][j] != 3) this.aiMap[i][j] = 5;
                if (i > 0 && j < this.aiMap[1].length - 1 && this.aiMap[i - 1][j + 1] == 3 && this.aiMap[i][j] != 3)
                    this.aiMap[i][j] = 5;
                if (i < this.aiMap.length - 1 && j > 0 && this.aiMap[i + 1][j - 1] == 3 && this.aiMap[i][j] != 3)
                    this.aiMap[i][j] = 5;
                if (i < this.aiMap.length - 1 && j < this.aiMap[1].length - 1 && this.aiMap[i + 1][j + 1] == 3 && this.aiMap[i][j] != 3)
                    this.aiMap[i][j] = 5;
            }
        }
    }

}