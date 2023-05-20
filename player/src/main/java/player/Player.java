package player;

import abstractClasses.Entity;
import interfaces.ICollision;
import utilities.Type;

public class Player extends Entity implements ICollision {

    @Override
    public void onCollision(Entity collidingEntity) {
        System.out.println(this.getHealth());

        if (collidingEntity.getType() == Type.BULLET) {
            this.setHealth(this.getHealth() - 1);
        }

        if (collidingEntity.getType() == Type.ENEMY) {
            this.setHealth(this.getHealth() - 5);
        }

        if (collidingEntity.getType() == Type.OBSTACLE) {
            this.setHealth(this.getHealth() - 1);
        }

    }
}
