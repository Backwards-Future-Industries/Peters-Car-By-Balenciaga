package utilities.image;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Image {

    private BufferedImage bufferedImage;
    private AffineTransform transform;

    Image(BufferedImage bufferedImage){
        this.bufferedImage = bufferedImage;
        this.transform = new AffineTransform();
    }

    public AffineTransform getTransform() {
        return transform;
    }
    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
}
