package collision;

import abstractClasses.Entity;
import interfaces.IProcessing;
import utilities.GameData;
import utilities.Inputs;
import utilities.Shapes;
import utilities.Types;

import java.util.ArrayList;

public class CollisionDetection implements IProcessing {

    @Override
    public void process(ArrayList<Inputs> inputs, GameData gameData) {

        //Processing collision in CollisionDetection, instead of on the individual components
        //Will try interface implementation instead for now to get high cohesion and low coupling
        for (Entity entity1 : gameData.getNewEntities()){
            for (Entity entity2: gameData.getNewEntities()){

                //check to see if they are the same entity
                if (entity1.equals(entity2)){
                    continue;
                }

                //Checks if entities are colliding
                if (this.isColliding(entity1,entity2)){
                    entity1.onCollision(entity2);
                    entity2.onCollision(entity1);

                    if (entity1.getType() == Types.OBSTACLE || entity2.getType() == Types.OBSTACLE) {
                        if (entity1.getType() == Types.OBSTACLE && entity2.getType() == Types.OBSTACLE) {
                            continue;
                        }
                        entity1.setPosition(obstacleCollision(entity1, entity2));
                    }

                    //Checks if player collides with bullet
                    //Bullet gets deleted and player loses health
                    /*if (entity1.getType() == Types.PLAYER && entity2.getType() == Types.BULLET) {
                        entity1.setHealth(entity1.getHealth()-1);
                        entity2.setHealth(entity2.getHealth()-1);
                    }

                    //Checks if player collides with enemy
                    //Both entities lose health (enemy loses more)
                    if (entity1.getType() == Types.PLAYER && entity2.getType() == Types.ENEMY) {
                        entity1.setHealth(entity1.getHealth()-5);
                        entity2.setHealth(entity2.getHealth()-8);
                    }

                    //Checks if player collides with an obstacle
                    //Player cant move past and loses health if moving too fast
                    if (entity1.getType() == Types.PLAYER && entity2.getType() == Types.OBSTACLE) {
                        entity1.setHealth(entity1.getHealth()-1);
                        entity1.setPosition(obstacleCollision(entity1,entity2));
                    }

                    //Kills bullet if it hits an obstacle
                    if (entity1.getType() == Types.BULLET && entity2.getType() == Types.OBSTACLE) {
                        entity1.setHealth(entity1.getHealth()-1);
                    }*/
                }
            }
        }
    }

    public boolean isColliding(Entity entity1, Entity entity2) {

        if (entity1.getType() == Types.UNDEFINED || entity2.getType() == Types.UNDEFINED){
            return false;
        }

        int[] e1Pos = entity1.getPosition();
        int[] e2Pos = entity2.getPosition();


        //Checks if entities are colliding with box collision
        Shapes[] e1Shape = entity1.getShape();
        for (Shapes e2Shape: entity2.getShape()){
            if (e1Pos[0] < e2Pos[0] + e2Shape.getWidth() &&
                    e1Pos[0] + e1Shape[0].getWidth() > e2Pos[0] &&
                    e1Pos[1] < e2Pos[1] + e2Shape.getHeight() &&
                    e1Pos[1] + e1Shape[0].getHeight() > e2Pos[1]) {
                return true;
            }
        }

        return false;
    }

    /**
     * Places Entity1 at the edge of the obstacle, in the direction that
     * Entity1 hit the obstacle from.
     * @param entity Entity of Type PLAYER or ENEMY
     * @param obstacle Entity of Type OBSTACLE
     * @return A new position for Entity1
     */
    public int[] obstacleCollision(Entity entity, Entity obstacle){

        int[] newPos = new int[]{0,0};

        int[] ePos = entity.getPosition();
        int[] oPos = obstacle.getPosition();

        Shapes[] eShape = entity.getShape();
        Shapes[] oShape = obstacle.getShape();

        //Entity is above obstacle
        if (ePos[0] < oPos[0] && ePos[1] < oPos[1]){
            newPos[0] = ePos[0];
            newPos[1] = oPos[1] - eShape[0].getHeight() - oShape[0].getHeight();
        }

        //Entity is below obstacle
        if (ePos[0] < oPos[0] && ePos[1] > oPos[1]){
            newPos[0] = ePos[0];
            newPos[1] = oPos[1] + eShape[0].getHeight() + oShape[0].getHeight();
        }

        //Entity is to the left of obstacle
        if (ePos[0] > oPos[0] && ePos[1] < oPos[1]){
            newPos[0] = oPos[0] - eShape[0].getWidth() - oShape[0].getWidth();
            newPos[1] = ePos[1];
        }

        //Entity is to the right of obstacle
        if (ePos[0] < oPos[0] && ePos[1] < oPos[1]){
            newPos[0] = oPos[0] + eShape[0].getWidth() + oShape[0].getWidth();
            newPos[1] = ePos[1];
        }

        return newPos;
    }
}

