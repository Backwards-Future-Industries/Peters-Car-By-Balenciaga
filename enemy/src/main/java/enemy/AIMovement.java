package enemy;

import abstractClasses.Entity;
import utilities.GameData;
import utilities.Inputs;
import utilities.Type;
import utilities.Vector2D;
import java.util.ArrayList;

public class AIMovement {
    private int[] enemyPosition;
    private Vector2D enemyDirection;
    private ArrayList<Inputs> inputs;

    private Vector2D direction;

    public AIMovement() {
        inputs = new ArrayList<>();
    }

    public ArrayList<Inputs> getInputs(GameData gameData, Entity enemyPlugin) {
        this.enemyPosition = enemyPlugin.getPosition();
        this.enemyDirection = enemyPlugin.getDirection();
        generateInputs(gameData);
        return inputs;
    }

    private void generateInputs(GameData gameData) {
        this.getDirection(gameData);

        double angle;
        double dotP;
        double crossP;


        if (this.enemyDirection.getLength() != 0 && this.direction != null) {
            dotP = this.direction.getX() * this.enemyDirection.getX() + this.direction.getY() * this.enemyDirection.getY();
            crossP = this.direction.getX() * this.enemyDirection.getY() - this.direction.getY() * this.enemyDirection.getX();
            angle = Math.atan2(crossP, dotP);
            if (angle > 0.5) this.inputs.add(Inputs.KEY_A);
            if (angle < -0.5) this.inputs.add(Inputs.KEY_D);
            if (this.direction.getLength() < 100 && this.enemyDirection.getLength() < 0.2 && this.enemyDirection.getLength() > 0) this.inputs.add(Inputs.KEY_S);
        }
        if (this.direction.getLength() > 100+this.enemyDirection.getLength()) this.inputs.add(Inputs.KEY_W);
    }

    private void getDirection(GameData gameData) {
        for (Entity entity : gameData.getEntityList(Type.PLAYER)) {
            int[] playerPosition = entity.getPosition();
            this.direction = new Vector2D((playerPosition[0] - this.enemyPosition[0]), (playerPosition[1] - this.enemyPosition[1]));
        }
    }
}

