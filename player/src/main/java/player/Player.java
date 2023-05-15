package player;

import abstractClasses.Entity;
import interfaces.ICollision;
import utilities.Types;

public class Player extends Entity implements ICollision {

    @Override
    public void onCollision(Entity collidingEntity) {

        if (collidingEntity.getType() == Types.BULLET) {
            this.setHealth(this.getHealth() - 1);
        }

        if (collidingEntity.getType() == Types.ENEMY) {
            this.setHealth(this.getHealth() - 5);
        }

        if (collidingEntity.getType() == Types.OBSTACLE) {
            this.setHealth(this.getHealth() - 1);
        }

    }
}
