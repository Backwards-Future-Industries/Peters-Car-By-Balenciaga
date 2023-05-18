import abstractClasses.Entity;
import interfaces.ICollision;

public class ConcreteEntity extends Entity implements ICollision {

    public ConcreteEntity() {
    }

    @Override
    public void onCollision(Entity entity) {
        this.setHealth(this.getHealth() - 1);
    }
}
