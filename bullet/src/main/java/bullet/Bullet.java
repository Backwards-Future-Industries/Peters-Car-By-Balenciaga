package bullet;

import abstractClasses.Entity;
import interfaces.ICollision;

public class Bullet extends Entity implements ICollision {

    public Bullet() {
    }

    @Override
    public void onCollision(Entity entity) {
        System.out.println("bing bong");
        this.setHealth(0);
    }

}
