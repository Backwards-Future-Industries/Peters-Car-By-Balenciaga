package enemy;

import abstractClasses.Entity;
import interfaces.ICollision;
import utilities.Type;

public class Enemy extends Entity implements ICollision {
    private long lastShot;

    public long getLastShot() {
        return lastShot;
    }

    public void setLastShot(long lastShot) {
        this.lastShot = lastShot;
    }

    @Override
    public void onCollision(Entity collidingEntity) {
        if (collidingEntity.getType() == Type.BULLET) {
            this.setHealth(this.getHealth() - 1);
        }

        if (collidingEntity.getType() == Type.PLAYER) {
            this.setHealth(this.getHealth() - 5);
        }

        if (collidingEntity.getType() == Type.OBSTACLE) {
            this.setHealth(this.getHealth() - 1);
        }
    }
}
