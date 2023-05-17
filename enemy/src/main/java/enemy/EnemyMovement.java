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

    private AIMovement aiMovement;


    @Override
    public void process(ArrayList<Inputs> inputs, GameData gameData) {
        for (Entity enemy : gameData.getEntityList(Type.ENEMY)) {
            aiMovement = new AIMovement(gameData, enemy);
            SPIlocator.getSpIlocator().getMovement().defaultMove(aiMovement.getInputsBasedOnAStar(), enemy, gameData);
            enemy.getSprite().freshRotate(enemy.getRadians(), enemy.getPosition());
        }
    }

    @Override
    public String toString() {
        return Type.ENEMY.toString();
    }
}
