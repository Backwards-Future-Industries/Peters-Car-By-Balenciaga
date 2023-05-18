package bullet;

import abstractClasses.Entity;
import interfaces.ICollision;

public class Bullet extends Entity implements ICollision {

    public Bullet() {
    }

    @Override
    public void onCollision(Entity entity) {
        this.setHealth(0);
    }

}
