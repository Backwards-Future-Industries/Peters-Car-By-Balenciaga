package collision;

import abstractClasses.Entity;
import interfaces.ICollision;
import interfaces.IProcessing;
import utilities.GameData;
import utilities.Inputs;
import utilities.Type;
import utilities.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class CollisionDetection implements IProcessing {

    private final int min_x = 0;
    private final int min_y = 0;
    private int max_x;
    private int max_y;

    public CollisionDetection() {
    }

    //SAT helper method
    private static boolean overlapOnAxis(Entity entity1, Entity entity2, Vector2D axis) {
        Vector2D interval1 = getInterval(entity1, axis);
        Vector2D interval2 = getInterval(entity2, axis);

        return ((interval2.getX() <= interval1.getY()) && (interval1.getX() <= interval2.getY()));
    }

    //SAT helper method
    private static Vector2D getInterval(Entity entity, Vector2D axis) {
        Vector2D result = new Vector2D(0, 0);

        Point[] points = entity.getSprite().updateTransformedRectangle(entity.getPosition()[0], entity.getPosition()[1], entity.getRadians());
        result.setX(axis.dot(Vector2D.pointToVector(points[0])));
        result.setY(result.getX());
        for (int i = 1; i < 4; i++) {
            double projection = axis.dot(Vector2D.pointToVector(points[i]));
            if (projection < result.getX()) {
                result.setX(projection);
            }
            if (projection > result.getY()) {
                result.setY(projection);
            }
        }

        return result;
    }

    private static LinkedList<Entity> collidableEntities(GameData gameData) {
        LinkedList<Entity> allEntities = new LinkedList<>();
        allEntities.addAll(gameData.getEntityList(Type.ENEMY));
        allEntities.addAll(gameData.getEntityList(Type.PLAYER));
        allEntities.addAll(gameData.getEntityList(Type.BULLET));
        allEntities.addAll(gameData.getEntityList(Type.OBSTACLE));
        return allEntities;
    }

    @Override
    public void process(ArrayList<Inputs> inputs, GameData gameData) {

        for (Entity entity1 : collidableEntities(gameData)) {

            setMinMaxValues(gameData);
            if (bulletKiller(entity1, gameData)) continue;

            for (Entity entity2 : collidableEntities(gameData)) {

                //check to see if they are the same entity
                if (entity1.equals(entity2)) continue;

                //Checks if entities are colliding
                if (entity1 instanceof ICollision && entity2 instanceof ICollision) {
                    if (this.isColliding(entity1, entity2)) {
                        ((ICollision) entity1).onCollision(entity2);
                        ((ICollision) entity2).onCollision(entity1);
                        avoidOverlap(entity1, entity2);
                    }
                }
            }
        }
    }

    public boolean isColliding(Entity entity1, Entity entity2) {
        //return isSATCollision(entity1, entity2);
        //in case SAT check somehow fails
        return isBoxCollision(entity1.getPosition(), entity2.getPosition(), new int[]{entity1.getSprite().getImage().getWidth(), entity1.getSprite().getImage().getHeight()}, new int[]{entity2.getSprite().getImage().getWidth(), entity2.getSprite().getImage().getHeight()});
    }

    /**
     * Checks if the two entities are colliding using Separating Axis Theorem collision detection
     * Credit to GamesWithGabe for the tutorial on SAT collision detection
     * Link to repo: https://github.com/codingminecraft/MarioYoutube/
     *
     * @param entity1 first shape
     * @param entity2 second shape
     * @return true if colliding, false if not
     */
    private boolean isSATCollision(Entity entity1, Entity entity2) {
        Vector2D[] axesToCheck = {new Vector2D(1, 0), new Vector2D(0, 1), new Vector2D(1, 0), new Vector2D(0, 1)};
        axesToCheck[0].rotateVector(entity1.getSprite().getRotation(), new Vector2D());
        axesToCheck[1].rotateVector(entity1.getSprite().getRotation(), new Vector2D());
        axesToCheck[2].rotateVector(entity2.getSprite().getRotation(), new Vector2D());
        axesToCheck[3].rotateVector(entity2.getSprite().getRotation(), new Vector2D());
        for (Vector2D axis : axesToCheck) {
            if (!overlapOnAxis(entity1, entity2, axis)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Avoids overlap between two entities by using their position,
     * dimensions and differences in position in the form of a minimum translation vector
     */
    private void avoidOverlap(Entity entity1, Entity entity2) {
        /*
        int[] newPos = new int[2];

        int[] e1Pos = entity1.getPosition();
        int[] e2Pos = entity2.getPosition();

        int e1width = entity1.getSprite().getImage().getWidth();
        int e1height = entity1.getSprite().getImage().getHeight();
        int e2width = entity2.getSprite().getImage().getWidth();
        int e2height = entity2.getSprite().getImage().getHeight();

        int dx =  calculateMTV(e1Pos[0], e1width, e2Pos[0], e2width);
        int dy =  calculateMTV(e1Pos[1], e1height, e2Pos[1], e2height);

        if(entity1.getPosition()[0] + dx < min_x){
        }
        else if (entity1.getPosition()[0] + dx > max_x) {
            newPos[0] = max_x;
        } else {
            newPos[0] = entity1.getPosition()[0] + dx;
        }
        if (entity1.getPosition()[1] + dy < min_y) {
        } else if (entity1.getPosition()[1] + dy > max_y) {
            newPos[1] = max_y;
        } else {
            newPos[1] = entity1.getPosition()[1] + dy;
        }*/
        entity1.setDirection(Vector2D.reverseVector(entity1.getDirection()));
        entity1.setRadians(entity1.getRadians() + Math.PI);
        //entity1.setPosition(newPos);
    }

    /**
     * Helper method for
     * Calculates the minimum translation vector for a collision between two entities
     * The MTV is needed for a more consistent better feeling reposition
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

        return overlap;
    }

    /**
     * A simple way for us to eliminate any bullets that get stuck at the edge of the screen
     *
     * @param entity   any entity
     * @param gameData the gameData
     * @return true if entity is a bullet and is outside the screen, false otherwise
     */
    private boolean bulletKiller(Entity entity, GameData gameData) {

        if (entity.getType() == Type.BULLET) {
            if (entity.getPosition()[0] > max_x - entity.getSprite().getImage().getWidth() || entity.getPosition()[0] < min_x || entity.getPosition()[1] > max_y - entity.getSprite().getImage().getHeight() || entity.getPosition()[1] < min_y) {
                ((ICollision) entity).onCollision(entity);
                return true;
            }
        }

        return false;
    }

    private void setMinMaxValues(GameData gameData) {
        max_x = (int) gameData.getScreenSize().getWidth();
        max_y = (int) gameData.getScreenSize().getHeight();
    }

    /**
     * Uses basic box collision to check if two entities collide,
     * deprecated as ideally we want to use SAT collision detection
     */
    @Deprecated
    private boolean isBoxCollision(int[] e1Pos, int[] e2Pos, int[] e1Dimmensions, int[] e2Dimensions) {
        if (e2Pos[0] < e1Pos[0] + e1Dimmensions[0] && e2Pos[0] + e2Dimensions[0] > e1Pos[0] && e2Pos[1] < e1Pos[1] + e1Dimmensions[1] && e2Pos[1] + e2Dimensions[1] > e1Pos[1]) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return Type.COLLISION.toString();
    }
}

