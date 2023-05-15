package interfaces;

import abstractClasses.Entity;

public interface ICollision {

    /**
     * Using the Type of collidingEntity, use this method to write the
     * the current entity's reaction to the collision
     * In case of no reaction, leave the method empty
     * @param collidingEntity the entity that the entity has collided with
     */
    void onCollision(Entity collidingEntity);

}
