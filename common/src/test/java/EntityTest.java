import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.image.Image;
import utilities.image.ImageLoader;

import java.awt.image.BufferedImage;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    private concreteEntity entity;
    private URL sprite;

    @BeforeEach
    void setUp() {
        this.sprite = EntityTest.class.getClassLoader().getResource("images/placeholder.png");
        this.entity = new concreteEntity(10,sprite);

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void setHealth() {
        this.entity.setHealth(20);
        assertEquals(20,this.entity.getHealth());
    }


    @Test
    void setSprite() {
        URL url = EntityTest.class.getClassLoader().getResource("images/placeholder.png");
        entity.setSprite(url, new double[]{1,1});
        Image image = ImageLoader.loadImage(url, new double[]{1,1});
        assertTrue(ImageComparator(image.getSourceImage(),entity.getSprite().getSourceImage()));
    }

    @Test
    void setPosition() {
        entity.setPosition(new int[]{1,1});
        assertArrayEquals(new int[]{1,1},entity.getPosition());
    }


    @Test
    void setRadius() {
        entity.setRadius(1);
        assertEquals(1,entity.getRadius());
    }


    private boolean ImageComparator(BufferedImage a, BufferedImage b){
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