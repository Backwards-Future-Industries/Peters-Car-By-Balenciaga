package collision;

import abstractClasses.Entity;
import interfaces.ICollision;
import interfaces.IProcessing;
import utilities.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class CollisionDetection implements IProcessing {

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

    public boolean isColliding(Entity entity1, Entity entity2) {

        int[] e1Pos = entity1.getPosition();
        int[] e2Pos = entity2.getPosition();

        //Checks if entities are colliding with box collision on Entity1
        Shapes[] e1Shapes = entity1.getShape();
        Shapes[] e2Shapes = entity2.getShape();
        //Checks through all entity1 shapes
        if (isBoxCollision(e1Pos, e2Pos, e1Shapes, e2Shapes)) return true;
        //Checks through all entity2 shapes to ensure that all shapes are checked
        return (isBoxCollision(e2Pos, e1Pos, e2Shapes, e1Shapes));
    }

    
    private boolean isBoxCollision(int[] e1Pos, int[] e2Pos, Shapes[] e1Shapes, Shapes[] e2Shapes) {
        for (Shapes e1Shape: e1Shapes){
            if (e2Pos[0] < e1Pos[0] + e1Shape.getWidth() &&
                    e2Pos[0] + e2Shapes[0].getWidth() > e1Pos[0] &&
                    e2Pos[1] < e1Pos[1] + e1Shape.getHeight() &&
                    e2Pos[1] + e2Shapes[0].getHeight() > e1Pos[1]) {
                return true;
            }
        }
        return false;
    }

    private boolean isSATCollision(Shapes[] e1Shapes, Shapes[] e2Shapes) {


        return false;
    }

    private static Vector2D getInterval(Entity entity, Vector2D axis) {
        Vector2D result = new Vector2D(0,0);

        result.setX(axis.dot(Vector2D.pointToVector(entity.getShape()[0].getPositions(entity.getSprite().getRotation())[0])));
        result.setY(result.getX());
        for (int i = 1; i < 4; i++){
            double projection = axis.dot(Vector2D.pointToVector(entity.getShape()[0].getPositions(entity.getSprite().getRotation())[i]));
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

    private LinkedList<Entity> collidableEntities(GameData gameData){
        LinkedList<Entity> allEntities = new LinkedList<>();
        allEntities.addAll(gameData.getEntityList(Type.ENEMY));
        allEntities.addAll(gameData.getEntityList(Type.PLAYER));
        allEntities.addAll(gameData.getEntityList(Type.BULLET));
        allEntities.addAll(gameData.getEntityList(Type.OBSTACLE));
        return allEntities;
    }
}

