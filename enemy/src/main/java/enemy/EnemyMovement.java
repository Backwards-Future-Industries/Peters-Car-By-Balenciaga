package enemy;

import abstractClasses.Entity;
import enemy.aiMovement.AIMovement;
import interfaces.IProcessing;
import utilities.GameData;
import utilities.Inputs;
import utilities.SPIlocator;
import utilities.Type;

import java.util.ArrayList;

public class EnemyMovement implements IProcessing {

    private AIMovement aiMovement = new AIMovement();


    @Override
    public void process(ArrayList<Inputs> inputs, GameData gameData) {
        for (Entity enemy : gameData.getEntityList(Type.ENEMY)) {
            aiMovement.updateData(gameData, enemy);
            ArrayList<Inputs> generatedInputs = aiMovement.getInputsBasedOnAStar();

            if (generatedInputs.contains(Inputs.KEY_SPACE)) {
                gameData.addBullet(Type.BULLET, enemy);
            }

            SPIlocator.getSpIlocator().getMovement().defaultMove(generatedInputs, enemy, gameData);
            enemy.getSprite().freshRotate(enemy.getRadians(), enemy.getPosition());
        }
    }

    @Override
    public String toString() {
        return Type.ENEMY.toString();
    }
}
