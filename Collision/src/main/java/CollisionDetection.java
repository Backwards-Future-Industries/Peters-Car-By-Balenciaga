import abstractClasses.Entity;
import interfaces.IProcessing;
import utilities.Inputs;
import java.util.ArrayList;

public class CollisionDetection implements IProcessing {

    //Need a way to access all existing enteties in case of a ittertion approach to
    //collision detection, alternatives are greatly appreciated.
    @Override
    public void process(ArrayList<Inputs> inputs) {

    }

    //Everything is a circle (for now?)
    public boolean isColliding (Entity entity1, Entity entity2){

        double[] ePos1 = entity1.getPosition();
        double[] ePos2 = entity2.getPosition();

        double dx = ePos1[0] - ePos2[0];
        double dy = ePos1[1] - ePos2[1];

        //Pythagoras to check distance between center points of enteties
        double distance = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));

        //Checks if the radius of the two points collide (collision)
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

}
