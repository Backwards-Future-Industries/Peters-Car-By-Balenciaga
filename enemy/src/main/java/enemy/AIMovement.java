package enemy;

import abstractClasses.Entity;
import utilities.GameData;
import utilities.Inputs;
import utilities.Types;
import utilities.Vector2D;

import java.util.AbstractList;
import java.util.ArrayList;

public class AIMovement {
    private int[] playerPosition;

    private final int[] enemyPosition;
    private final Vector2D enemyDirection;
    private ArrayList<Inputs> inputs;

    private Vector2D direction;

    public AIMovement(GameData gameData, Entity enemyPlugin) {
        for (Entity entity : gameData.getNewEntities()) {
            if (entity.getType() == Types.PLAYER) {
                playerPosition = entity.getPosition();
            }
        }
        this.enemyPosition = enemyPlugin.getPosition();
        this.enemyDirection = enemyPlugin.getDirection();
        assert playerPosition != null;
        direction = new Vector2D((playerPosition[0] - enemyPosition[0]), (playerPosition[1] - enemyPosition[1]));
    }

    public ArrayList<Inputs> getInputs() {
        generateInputs();
        return inputs;
    }

    private void generateInputs() {
        double angle = 0;
        double dotP;
        double crossP;
        if (enemyDirection.getLength() != 0) {
            dotP = direction.getX() * enemyDirection.getX() + direction.getY() * enemyDirection.getY();
            crossP = direction.getX() * enemyDirection.getY() - direction.getY() * enemyDirection.getX();
            angle = Math.atan2(crossP, dotP);
            if (angle > 0) this.inputs.add(Inputs.KEY_A);
            if (angle < 0) this.inputs.add(Inputs.KEY_D);
        }
        this.inputs.add(Inputs.KEY_W);
    }
}

