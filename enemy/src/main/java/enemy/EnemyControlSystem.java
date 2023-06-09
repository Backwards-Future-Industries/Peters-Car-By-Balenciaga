package enemy;

import abstractClasses.Entity;
import enemy.aiMovement.AIMovement;
import interfaces.IProcessing;
import utilities.GameData;
import utilities.Inputs;
import utilities.SPILocator;
import utilities.Type;

import java.util.ArrayList;

public class EnemyControlSystem implements IProcessing {

    private AIMovement aiMovement = new AIMovement();


    @Override
    public void process(ArrayList<Inputs> inputs, GameData gameData) {
        for (Entity enemy : gameData.getEntityList(Type.ENEMY)) {
            aiMovement.updateData(gameData, enemy);
            ArrayList<Inputs> generatedInputs = aiMovement.getInputsBasedOnAStar();

            if (enemy.getHealth() == 0) {
                SPILocator.getSpIlocator().getPluginMap().get(enemy.getType()).delete(gameData, enemy);
            }

            shoot(gameData, enemy, generatedInputs);
            SPILocator.getSpIlocator().getMovement().defaultMove(generatedInputs, enemy, gameData);
            enemy.getSprite().freshRotate(enemy.getRadians(), enemy.getPosition());


        }
    }

    public boolean shoot(GameData gameData, Entity enemy, ArrayList<Inputs> generatedInputs) {
        if (generatedInputs.contains(Inputs.KEY_SPACE)) {
            gameData.addBullet(Type.BULLET, enemy);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return Type.ENEMY.toString();
    }
}