package utilities.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageLoader {

    public static Image loadImage(URL url,double[] scale){
        Image image;
        try {
            image = new Image(ImageIO.read(url),scale);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return image;
    }

    public static Image loadImage(BufferedImage bufferedImage,double[] scale){
        Image image = new Image(bufferedImage,scale);

        return image;
    }



    //https://stackoverflow.com/questions/15305037/java-compare-one-bufferedimage-to-another
    public static boolean Comparator(BufferedImage a, BufferedImage b){
        if (a.getWidth() == b.getWidth() && a.getHeight() == b.getHeight()){
            for (int i = 0; i< a.getWidth()* a.getHeight(); i++){
                int row = i / a.getWidth();
                int column = i % a.getHeight();
                if (a.getRGB(row,column)!=b.getRGB(row,column))return false;
            }
        }else {
            return false;
        }
        return true;
    }
}
