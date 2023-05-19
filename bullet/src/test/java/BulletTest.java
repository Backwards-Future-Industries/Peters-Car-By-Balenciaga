import abstractClasses.Entity;
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

        //Act
        Entity bullet = iBulletService.create(new int[]{2,4},2.5);
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
        IBulletService iBulletService = new BulletPlugin();

        //Act
        Entity entity = iBulletService.create(new int[]{2,4},2);

        //Assert
        Assertions.assertEquals( Type.BULLET,entity.getType());
        Assertions.assertArrayEquals(new int[]{2,4},entity.getPosition());
        Assertions.assertEquals(2,entity.getRadians());
    }

    @Test
    void testDelete(){
        //Arrange
        LinkedList<Entity> entityMap = new LinkedList<>();
        IBulletService iBulletService = new BulletPlugin();
        GameData gameData = new GameData();

        //Act
        gameData.addNewEntity(iBulletService.create(new int[]{2,2},2));
        iBulletService.delete(gameData,gameData.getEntityList(Type.BULLET).get(0));

        //Assert
        Assertions.assertEquals(entityMap,gameData.getEntityList(Type.BULLET));
    }

}