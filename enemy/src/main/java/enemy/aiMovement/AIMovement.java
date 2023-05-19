package enemy.aiMovement;

import abstractClasses.Entity;
import enemy.Enemy;
import utilities.GameData;
import utilities.Inputs;
import utilities.Type;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class AIMovement {
    private Enemy enemyPlugin;
    private double distance;
    private int[] enemyPosition;
    private int[] playerPosition;
    private Vector2D enemyDirection;
    private AStarSearch aStarSearch;
    private Node enemyNode;
    private Node playerNode;
    private ArrayList<Inputs> inputs;
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
        this.distance = new Vector2D(playerPosition[0] - enemyPosition[0], playerPosition[1] - enemyPosition[1]).getLength();
        this.enemyNode = new Node(this.enemyPosition[0] / 16, this.enemyPosition[1] / 16);
        this.playerNode = new Node(this.playerPosition[0] / 16, this.playerPosition[1] / 16);
        if (this.playerNode.getX() == 80) this.playerNode.setX(79);
        if (this.playerNode.getY() == 60) this.playerNode.setY(59);
        if (gameData.getMap().getAiMap() == null) {
            gameData.getMap().setAiMap(new int[80][60]);
        }
        this.aStarSearch = new AStarSearch(gameData.getMap().getAiMap());
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
        int[] goal = new int[2];
        if (path.size() == 1) {
            generateInputs(enemyPosition, playerPosition);
        } else {
            goal[0] = path.get(1).getX() * 16 + 8;
            goal[1] = path.get(1).getY() * 16 + 8;
            generateInputs(enemyPosition, goal);
        }
        return inputs;
    }

    private void generateInputs(int[] start, int[] goal) {
        Vector2D direction = new Vector2D((goal[0] - start[0]), (goal[1] - start[1]));
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

    private int[] getPlayerPosition(GameData gameData) {
        for (Entity entity : gameData.getEntityList(Type.PLAYER)) {
            return entity.getPosition();
        }
        return enemyPosition;
    }

    private void aiShoot() {
        if (enemyPlugin != null) {
            if (System.currentTimeMillis() - this.enemyPlugin.getLastShot() > 2000 && this.distance < 150 && this.distance > 10) {
                this.inputs.add(Inputs.KEY_SPACE);
                this.enemyPlugin.setLastShot(System.currentTimeMillis());
            }
        }
    }
}

