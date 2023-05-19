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

        for (Entity entity1: collidableEntities(gameData)){

            if(bulletKiller(entity1,gameData)) continue;

            for (Entity entity2: collidableEntities(gameData)){

                //check to see if they are the same entity
                if (entity1.equals(entity2)) continue;

                //Checks if entities are colliding
                if (entity1 instanceof ICollision && entity2 instanceof ICollision){
                    if (this.isColliding(entity1,entity2)){
                        ((ICollision) entity1).onCollision(entity2);
                        ((ICollision) entity2).onCollision(entity1);
                        if (entity1.getType() != Type.OBSTACLE || entity2.getType() != Type.OBSTACLE){
                           resolveCollision(entity1,entity2);
                        }
                    }
                }
            }
        }
    }

    private boolean isColliding(Entity entity1, Entity entity2) {
        //return isSATCollision(entity1, entity2);
        //in case SAT check somehow fails
        return isBoxCollision(
                entity1.getPosition(),
                entity2.getPosition(),
                new int[]{entity1.getSprite().getImage().getWidth(),
                        entity1.getSprite().getImage().getHeight()},
                new int[]{entity2.getSprite().getImage().getWidth(),
                        entity2.getSprite().getImage().getHeight()});
    }

    /**
     * Checks if the two entities are colliding using Separating Axis Theorem collision detection
     * Credit to GamesWithGabe for the tutorial on SAT collision detection
     * Link to repo: https://github.com/codingminecraft/MarioYoutube/
     * @param entity1 first shape
     * @param entity2 second shape
     * @return true if colliding, false if not
     */
    private boolean isSATCollision(Entity entity1, Entity entity2){
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

        result.setX(axis.dot(Vector2D.pointToVector(entity.getSprite().getPoints()[0])));
        result.setY(result.getX());
        for (int i = 1; i < 4; i++){
            double projection = axis.dot(Vector2D.pointToVector(entity.getSprite().getPoints()[i]));
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
     * Resolves the collision between two entities ensuring that they do not overlap
     */
    private void resolveCollision(Entity entity1, Entity entity2){

        int dx =  calculateMTV(
                entity1.getPosition()[0],
                entity1.getSprite().getImage().getWidth(),
                entity2.getPosition()[0],
                entity2.getSprite().getImage().getWidth());
        int dy =  calculateMTV(
                entity1.getPosition()[1],
                entity1.getSprite().getImage().getHeight(),
                entity2.getPosition()[1],
                entity2.getSprite().getImage().getHeight());



        entity1.setPosition(new int[]{entity1.getPosition()[0] + dx, entity1.getPosition()[1] + dy});
    }

    /**
     * Helper method for
     * Calculates the minimum translation vector for a collision between two entities
     */
    private int calculateMTV(int posA, int sizeA, int posB, int sizeB) {
        int halfA = sizeA / 2;
        int halfB = sizeB / 2;

        int centerA = posA + halfA;
        int centerB = posB + halfB;

        int distance = Math.abs(centerA - centerB);
        int overlap = halfA + halfB - distance;

        if (overlap > 0) {
            if (centerA < centerB) {
                return -overlap;
            } else {
                return overlap;
            }
        }

        return 0;
    }


    /**
     * A simple way for us to eliminate any bullets that get stuck at the edge of the screen
     * @param entity any entity
     * @param gameData the gameData
     * @return true if entity is a bullet and is outside the screen, false otherwise
     */
    private boolean bulletKiller(Entity entity, GameData gameData) {

        int max_x = (int) gameData.getScreenSize().getWidth();
        int max_y = (int) gameData.getScreenSize().getHeight();
        int min_x = 0;
        int min_y = 0;

        if (entity.getType()==Type.BULLET){
            if (entity.getPosition()[0] > max_x - entity.getSprite().getImage().getWidth()
                    || entity.getPosition()[0] < min_x
                    || entity.getPosition()[1] > max_y - entity.getSprite().getImage().getHeight()
                    || entity.getPosition()[1] < min_y){
                ((ICollision) entity).onCollision(entity);
                return true;
            }
        }

        return false;
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
    private boolean isBoxCollision(int[] e1Pos, int[] e2Pos, int[] e1Dimmensions, int[] e2Dimensions) {
        if (e2Pos[0] < e1Pos[0] + e1Dimmensions[0] &&
                e2Pos[0] + e2Dimensions[0] > e1Pos[0] &&
                e2Pos[1] < e1Pos[1] + e1Dimmensions[1] &&
                e2Pos[1] + e2Dimensions[1] > e1Pos[1]) {
            return true;
        }

        return false;
    }

    @Override
    public String toString(){
        return Type.COLLISION.toString();
    }
}

