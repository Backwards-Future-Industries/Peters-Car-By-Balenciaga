import abstractClasses.Entity;
import bullet.Bullet;
import bullet.BulletPlugin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.GameData;
import utilities.Type;
import utilities.Vector2D;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BulletTest {

    //Arrange
    private Point position;
    private Vector2D direction;
    private Bullet bullet;
    private BulletPlugin iBulletService;
    private GameData gameData;

    @BeforeEach
    void setUp() {
        //Arrange
        position = new Point(2, 4);
        direction = new Vector2D(20, 50);
        bullet = new Bullet();
        iBulletService = new BulletPlugin();
        gameData = new GameData();
    }


    @Test
    void testVariables() {

        //Act
        bullet.setPosition(new Point(2, 4));
        bullet.setHealth(1);
        bullet.setAcceleration(1);
        bullet.setMaxSpeed(3);
        bullet.setDirection(new Vector2D(20, 50));

        //Assert
        assertEquals(position.x, bullet.getPosition().x);
        assertEquals(position.y, bullet.getPosition().y);
        assertArrayEquals(direction.getComponents(), bullet.getDirection().getComponents());
        assertEquals(bullet.getHealth(), 1);
        assertEquals(bullet.getAcceleration(), 1);
        assertEquals(bullet.getMaxSpeed(), 3);
    }

    @Test
    void testCreate() {
        //Act
        bullet.setPosition(new Point(2, 4));
        bullet.setType(Type.BULLET);
        bullet.setRadians(2);

        //Assert
        Assertions.assertEquals(Type.BULLET, bullet.getType());
        Assertions.assertEquals(new Point(2, 4), bullet.getPosition());
        Assertions.assertEquals(2, bullet.getRadians());
    }

    @Test
    void testDelete() {
        //Act
        Entity entity = iBulletService.create(bullet);
        gameData.addNewEntity(entity);
        iBulletService.delete(gameData, gameData.getEntityList(Type.BULLET).getFirst());

        //Assert
        Assertions.assertTrue(gameData.getEntityList(Type.BULLET).isEmpty());
    }

}