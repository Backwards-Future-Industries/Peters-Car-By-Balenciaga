package enemy.aiMovement;

import abstractClasses.Entity;
import enemy.Enemy;
import utilities.GameData;
import utilities.Inputs;
import utilities.Type;
import utilities.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AIMovement {
    private final ArrayList<Inputs> inputs;
    private Enemy enemyPlugin;
    private double distance;
    private Point enemyPosition;
    private Point playerPosition;
    private Vector2D enemyDirection;
    private List<Node> path;

    public AIMovement() {
        this.inputs = new ArrayList<>();
    }

    public void updateData(GameData gameData, Entity enemyPlugin) {
        if (enemyPlugin instanceof Enemy) {
            this.enemyPlugin = ((Enemy) enemyPlugin);
        }
        this.enemyPosition = enemyPlugin.getPosition();
        this.playerPosition = this.getPlayerPosition(gameData);
        this.enemyDirection = enemyPlugin.getDirection();
        this.distance = new Vector2D(playerPosition.x - enemyPosition.x, playerPosition.y - enemyPosition.y).getLength();
        Node enemyNode = new Node(this.enemyPosition.x / 16, this.enemyPosition.y / 16);
        Node playerNode = new Node(this.playerPosition.x / 16, this.playerPosition.y / 16);

        enemyNode.setX(Math.min(enemyNode.getX(), 79));
        enemyNode.setY(Math.min(enemyNode.getY(), 59));
        playerNode.setX(Math.min(playerNode.getX(), 79));
        playerNode.setY(Math.min(playerNode.getY(), 59));

        if (gameData.getMap().getAiMap() == null) {
            gameData.getMap().setAiMap(new int[80][60]);
        }
        AStarSearch aStarSearch = new AStarSearch(gameData.getMap().getAiMap());
        if (aStarSearch.getMap()[playerNode.getY()][playerNode.getX()] > 2) {
            this.path = aStarSearch.findPath(enemyNode, enemyNode);
        } else {
            this.path = aStarSearch.findPath(enemyNode, playerNode);
        }
        this.inputs.clear();
    }

    public ArrayList<Inputs> getInputsBasedOnAStar() {
        if (path.size() == 0) {
            generateInputs(enemyPosition, playerPosition);
            return inputs;
        }
        aiShoot();
        Point goal = new Point();
        if (path.size() == 1) {
            generateInputs(enemyPosition, playerPosition);
        } else {
            goal.x = path.get(1).getX() * 16 + 8;
            goal.y = path.get(1).getY() * 16 + 8;
            generateInputs(enemyPosition, goal);
        }
        return inputs;
    }

    private void generateInputs(Point start, Point goal) {
        Vector2D direction = new Vector2D((goal.x - start.x), (goal.y - start.y));
        double angle;
        double dotP;
        double crossP;

        if (this.enemyDirection.getLength() != 0) {
            dotP = direction.getX() * this.enemyDirection.getX() + direction.getY() * this.enemyDirection.getY();
            crossP = direction.getX() * this.enemyDirection.getY() - direction.getY() * this.enemyDirection.getX();
            angle = Math.atan2(crossP, dotP);
            if (angle > 0.5) this.inputs.add(Inputs.KEY_A);
            if (angle < -0.5) this.inputs.add(Inputs.KEY_D);
            if (direction.getLength() < 100 && this.enemyDirection.getLength() < 0.2 && this.enemyDirection.getLength() > 0)
                this.inputs.add(Inputs.KEY_S);
        }
        if (direction.getLength() > this.enemyDirection.getLength()) this.inputs.add(Inputs.KEY_W);
    }

    private Point getPlayerPosition(GameData gameData) {
        for (Entity entity : gameData.getEntityList(Type.PLAYER)) {
            return entity.getPosition();
        }
        return enemyPosition;
    }

    private void aiShoot() {
        if (enemyPlugin != null) {
            if (System.currentTimeMillis() - this.enemyPlugin.getLastShot() > 2000 && this.distance < 250 && this.distance > 10) {
                this.inputs.add(Inputs.KEY_SPACE);
                this.enemyPlugin.setLastShot(System.currentTimeMillis());
            }
        }
    }
}

