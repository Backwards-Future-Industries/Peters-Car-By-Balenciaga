import abstractClasses.Entity;
import interfaces.IProcessing;

public class CollisionDetection implements IProcessing {

    //Need a way to access all existing enteties in case of a ittertion approach to
    //collision detection, alternatives are greatly appreciated.
    @Override
    public void process() {

    }


    //We need to agree on a radius or something similar for this to work, obviously.

    public boolean isColliding (Entity entity1, Entity entity2){
        return entity1.getPosition() == entity2.getPosition();
    }
}
