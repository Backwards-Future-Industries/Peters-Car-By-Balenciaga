package player;

import abstractClasses.Entity;
import utilities.Types;

public class Player extends Entity {

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
