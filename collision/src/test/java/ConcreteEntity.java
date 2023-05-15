import abstractClasses.Entity;
import utilities.Types;

import java.net.URL;

public class ConcreteEntity extends Entity {

    public ConcreteEntity(int health, URL sprite) {
        super(health, sprite, Types.ENEMY);
    }

    @Override
    public void onCollision(Entity collidingEntity) {

    }
}
