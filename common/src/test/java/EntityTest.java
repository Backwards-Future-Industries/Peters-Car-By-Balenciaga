import abstractClasses.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utilities.image.Image;
import utilities.image.ImageLoader;

import java.awt.*;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class EntityTest {

    @Mock
    private Entity entity;
    private URL sprite;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.sprite = EntityTest.class.getClassLoader().getResource("images/placeholder.png");
        this.entity.setHealth(10);
        this.entity.setSprite(sprite, new double[]{1, 1});

    }


    @Test
    void setHealth() {
        when(this.entity.getHealth()).thenReturn(20);
        this.entity.setHealth(20);
        assertEquals(20, this.entity.getHealth());
    }


    @Test
    void setSprite() {
        when(this.entity.getSprite()).thenReturn(ImageLoader.loadImage(sprite, new double[]{1, 1}));
        URL url = EntityTest.class.getClassLoader().getResource("images/placeholder.png");
        entity.setSprite(url, new double[]{1, 1});
        Image image = ImageLoader.loadImage(url, new double[]{1, 1});
        assertTrue(ImageLoader.Comparator(image.getSourceImage(), entity.getSprite().getSourceImage()));
    }

    @Test
    void setPosition() {
        when(this.entity.getPosition()).thenReturn(new Point(1, 1));
        entity.setPosition(new Point(1, 1));
        assertEquals(new Point(1, 1), entity.getPosition());
    }


}