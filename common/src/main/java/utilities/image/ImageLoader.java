package utilities.image;

import javax.imageio.ImageIO;
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
}
