import abstractClasses.Entity;
import bullet.Bullet;
import bullet.BulletPlugin;
import interfaces.IBulletService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilities.GameData;
import utilities.Type;
import utilities.Vector2D;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BulletTest {

    @Test
    void testVariables() {
        //Arrange
        IBulletService iBulletService = new BulletPlugin();
        int[] position = {2, 4};
        Vector2D direction = new Vector2D(20,50);
        Entity bullet = new Bullet();

        //Act
        bullet.setPosition(new int[]{2,4});
        bullet.setHealth(1);
        bullet.setAcceleration(1);
        bullet.setMaxSpeed(3);
        bullet.setDirection(new Vector2D(20,50));

        //Assert
        assertArrayEquals(position, bullet.getPosition());
        assertArrayEquals(direction.getComponents(), bullet.getDirection().getComponents());
        assertEquals(bullet.getHealth(), 1);
        assertEquals(bullet.getAcceleration(), 1);
        assertEquals(bullet.getMaxSpeed(), 3);
    }

    @Test
    void testCreate(){
        //Arrange
        ConcreteBulletEntity entity = new ConcreteBulletEntity();

        //Act
        entity.setPosition(new int[]{2,4});
        entity.setType(Type.BULLET);
        entity.setRadians(2);

        //Assert
        Assertions.assertEquals( Type.BULLET,entity.getType());
        Assertions.assertArrayEquals(new int[]{2,4},entity.getPosition());
        Assertions.assertEquals(2,entity.getRadians());
    }

    @Test
    void testDelete(){
        //Arrange
        LinkedList<Entity> entityMap = new LinkedList<>();
        BulletPlugin iBulletService = new BulletPlugin();
        ConcreteBulletEntity bullet = new ConcreteBulletEntity();
        GameData gameData = new GameData();

        //Act
        Entity entity = iBulletService.create(bullet);
        gameData.addNewEntity(entity);
        iBulletService.delete(gameData,gameData.getEntityList(Type.BULLET).getFirst());

        //Assert
        Assertions.assertTrue(gameData.getEntityList(Type.BULLET).isEmpty());
    }

}