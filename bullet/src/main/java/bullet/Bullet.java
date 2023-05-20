package bullet;

import abstractClasses.Entity;
import interfaces.ICollision;
import utilities.Type;

public class Bullet extends Entity implements ICollision {

    public Bullet() {
    }

    @Override
    public void onCollision(Entity entity) {
        if (entity.getType()== Type.BULLET){
            this.setHealth(this.getHealth()-1);
        } else {
            this.setHealth(0);
        }
    }

}
