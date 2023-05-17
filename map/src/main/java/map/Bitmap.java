package map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Bitmap {

    private TileType[][] map;
    private BufferedImage bitmap;

    public Bitmap(){
        URL url = Bitmap.class.getResource("/bitmaps/bitMapMaze2.png");
        try {
            bitmap = ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.map = new TileType[bitmap.getWidth()][bitmap.getHeight()];
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
            }
        }

    }

    private TileType findTile(int blue, int green, int red){
        Color color = Color.cyan;
        if(blue > 250 && green > 250 && red > 250){
            color = Color.black;
        }

        if(blue < 5 && green < 5 && red < 5){
            color = Color.white;
        }

        if (blue < 5 && green < 5 && red > 250){
            color = Color.red;
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
        return TileType.BLANK;
    }

    public TileType[][] getMap() {
        return map;
    }
}
