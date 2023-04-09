import abstractClasses.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

public class PlayerPluginTest {
    private PlayerPlugin playerPlugin;
    private double[] list;

    @BeforeEach
    void setPlayerPlugin(){
        this.playerPlugin = new PlayerPlugin();
        this.list = new double[2];
        this.list[0] = 10;
        this.list[1] = 10;

    }

    @Test
    public void testReturnType(){
        Assertions.assertEquals(this.list[0],playerPlugin.create().getPosition()[0]);
        Assertions.assertEquals(this.list[1],playerPlugin.create().getPosition()[1]);
    }
}



