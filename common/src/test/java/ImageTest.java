import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.Image;
import utilities.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {

    Image image;
    URL url;
    BufferedImage bufferedImage;

    @BeforeEach
    void setUp() throws IOException {
        this.url = ImageTest.class.getClassLoader().getResource("images/placeholder.png");
        this.image = ImageLoader.loadImage(url,new double[]{0.5,0.5});
        this.bufferedImage = ImageIO.read(url);
    }

    @Test
    void getSourceImage() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(url);
        assertTrue(ImageLoader.Comparator(bufferedImage,image.getSourceImage()));
    }

    @Test
    void getImage() throws IOException {
        assertTrue(ImageLoader.Comparator(bufferedImage,image.getSourceImage()));
    }

    @Test
    void scale() {
        assertNotEquals(image.getSourceImage().getHeight(),image.getImage().getHeight());
        assertNotEquals(image.getSourceImage().getWidth(),image.getImage().getWidth());
    }

    @Test
    void rotate() {
        int[] position = {1,1};
        double rotation = 2;

        AffineTransform transform = AffineTransform.getRotateInstance(rotation,position[0]+image.getImage().getWidth()/2,position[1]+image.getImage().getHeight()/2);
        image.rotate(rotation,position);
        assertTrue(transform.equals(image.getTransform()));
    }

    @Test
    void freshRotate() {
        int[] position = {1,1};
        double rotation = 2;
        AffineTransform transform = AffineTransform.getRotateInstance(rotation,position[0]+image.getImage().getWidth()/2,position[1]+image.getImage().getHeight()/2);
        image.rotate(rotation,position);
        assertTrue(transform.equals(image.getTransform()));
    }
}