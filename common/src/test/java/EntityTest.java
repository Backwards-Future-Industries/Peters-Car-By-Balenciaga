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
        this.entity = new concreteEntity();
        this.entity.setHealth(10);
        this.entity.setSprite(sprite,new double[]{1,1});

    }


    @Test
    void setHealth() {
        this.entity.setHealth(20);
        assertEquals(20,this.entity.getHealth());
    }


    @Test
    void setSprite() {
        URL url = EntityTest.class.getClassLoader().getResource("images/placeholder.png");
        entity.setSprite(url, new double[]{1,1},true);
        Image image = ImageLoader.loadImage(url, new double[]{1,1});
        assertTrue(ImageLoader.Comparator(image.getSourceImage(),entity.getSprite().getSourceImage()));
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
}