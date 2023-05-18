package collision;

import abstractClasses.Entity;
import interfaces.ICollision;
import interfaces.IProcessing;
import utilities.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class CollisionDetection implements IProcessing {

    public CollisionDetection() {
    }

    @Override
    public void process(ArrayList<Inputs> inputs, GameData gameData) {

        for (Entity entity1 : collidableEntities(gameData)){
            for (Entity entity2: collidableEntities(gameData)){

                //check to see if they are the same entity
                if (entity1.equals(entity2)) continue;

                //Checks if entities are colliding
                if (entity1 instanceof ICollision && entity2 instanceof ICollision){
                    if (this.isColliding(entity1,entity2)){
                        ((ICollision) entity1).onCollision(entity2);
                        ((ICollision) entity2).onCollision(entity1);

                        if (entity1.getType() == Type.OBSTACLE || entity2.getType() == Type.OBSTACLE) {
                            if (entity1.getType() == Type.OBSTACLE && entity2.getType() == Type.OBSTACLE) {
                                continue;
                            }
                            entity1.setPosition(obstacleCollision(entity1, entity2));
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks if the two entities are colliding using Separating Axis Theorem collision detection
     * Credit to GamesWithGabe for the tutorial on SAT collision detection
     * Link to repo: https://github.com/codingminecraft/MarioYoutube/
     * @param entity1 first shape
     * @param entity2 second shape
     * @return true if colliding, false if not
     */
    public boolean isColliding(Entity entity1, Entity entity2) {
        Vector2D[] axesToCheck = {
                new Vector2D(1,0), new Vector2D(0,1),
                new Vector2D(1,0), new Vector2D(0,1)
        };
        axesToCheck[0].rotateVector(entity1.getSprite().getRotation(), new Vector2D());
        axesToCheck[1].rotateVector(entity1.getSprite().getRotation(), new Vector2D());
        axesToCheck[2].rotateVector(entity2.getSprite().getRotation(), new Vector2D());
        axesToCheck[3].rotateVector(entity2.getSprite().getRotation(), new Vector2D());
        for(Vector2D axis : axesToCheck) {
            if(!overlapOnAxis(entity1, entity2, axis)) {
                return false;
            }
        }

        return true;
    }

    //SAT helper method
    private static boolean overlapOnAxis(Entity entity1, Entity entity2, Vector2D axis) {
        Vector2D interval1 = getInterval(entity1, axis);
        Vector2D interval2 = getInterval(entity2, axis);

        return ((interval2.getX() <= interval1.getY()) && (interval1.getX() <= interval2.getY()));
    }

    //SAT helper method
    private static Vector2D getInterval(Entity entity, Vector2D axis) {
        Vector2D result = new Vector2D(0,0);

        result.setX(axis.dot(Vector2D.pointToVector(entity.getShape().getPositions(entity.getSprite().getRotation())[0])));
        result.setY(result.getX());
        for (int i = 1; i < 4; i++){
            double projection = axis.dot(Vector2D.pointToVector(entity.getShape().getPositions(entity.getSprite().getRotation())[i]));
            if (projection < result.getX()){
                result.setX(projection);
            }
            if (projection > result.getY()){
                result.setY(projection);
            }
        }

        return result;
    }



    /**
     * Places entity at the edge of the obstacle, in the direction that
     * entity hit the obstacle from.
     * @param entity Entity of Type PLAYER or ENEMY
     * @param obstacle Entity of Type OBSTACLE
     * @return A new position for Entity1
     */
    private int[] obstacleCollision(Entity entity, Entity obstacle){

        int[] newPos = new int[]{0,0};

        int[] ePos = entity.getPosition();
        int[] oPos = obstacle.getPosition();

        Shapes eShape = entity.getShape();
        Shapes oShape = obstacle.getShape();

        //Entity is above obstacle
        if (ePos[0] < oPos[0] && ePos[1] < oPos[1]){
            newPos[0] = ePos[0];
            newPos[1] = oPos[1] - eShape.getHeight() - oShape.getHeight();
        }

        //Entity is below obstacle
        if (ePos[0] < oPos[0] && ePos[1] > oPos[1]){
            newPos[0] = ePos[0];
            newPos[1] = oPos[1] + eShape.getHeight() + oShape.getHeight();
        }

        //Entity is to the left of obstacle
        if (ePos[0] > oPos[0] && ePos[1] < oPos[1]){
            newPos[0] = oPos[0] - eShape.getWidth() - oShape.getWidth();
            newPos[1] = ePos[1];
        }

        //Entity is to the right of obstacle
        if (ePos[0] < oPos[0] && ePos[1] < oPos[1]){
            newPos[0] = oPos[0] + eShape.getWidth() + oShape.getWidth();
            newPos[1] = ePos[1];
        }

        return newPos;
    }

    private static LinkedList<Entity> collidableEntities(GameData gameData){
        LinkedList<Entity> allEntities = new LinkedList<>();
        allEntities.addAll(gameData.getEntityList(Type.ENEMY));
        allEntities.addAll(gameData.getEntityList(Type.PLAYER));
        allEntities.addAll(gameData.getEntityList(Type.BULLET));
        allEntities.addAll(gameData.getEntityList(Type.OBSTACLE));
        return allEntities;
    }

    /**
     * Uses basic box collision to check if two entities collide,
     * deprecated as ideally we want to use SAT collision detection
     */
    @Deprecated
    private boolean isBoxCollision(int[] e1Pos, int[] e2Pos, Shapes e1Shape, Shapes e2Shape) {
        if (e2Pos[0] < e1Pos[0] + e1Shape.getWidth() &&
                e2Pos[0] + e2Shape.getWidth() > e1Pos[0] &&
                e2Pos[1] < e1Pos[1] + e1Shape.getHeight() &&
                e2Pos[1] + e2Shape.getHeight() > e1Pos[1]) {
            return true;
        }

        return false;
    }

}

