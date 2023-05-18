import abstractClasses.Entity;
import interfaces.ICollision;
import utilities.Type;

import java.net.URL;

public class ConcreteEntity extends Entity implements ICollision {

    public ConcreteEntity() {
    }

    @Override
    public void onCollision(Entity collidingEntity) {
        this.setHealth(this.getHealth() - 1);
    }
}
