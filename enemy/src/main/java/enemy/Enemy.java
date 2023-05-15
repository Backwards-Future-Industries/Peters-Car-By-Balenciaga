package enemy;

import abstractClasses.Entity;
import interfaces.ICollision;
import utilities.Types;

public class Enemy extends Entity implements ICollision {
    @Override
    public void onCollision(Entity collidingEntity) {
        if (collidingEntity.getType() == Types.BULLET) {
            this.setHealth(this.getHealth() - 1);
        }

        if (collidingEntity.getType() == Types.PLAYER) {
            this.setHealth(this.getHealth() - 5);
        }

        if (collidingEntity.getType() == Types.OBSTACLE) {
            this.setHealth(this.getHealth() - 1);
        }
    }
}
