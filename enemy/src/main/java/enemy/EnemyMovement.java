package enemy;

import abstractClasses.Entity;
import enemy.aiMovement.AIMovement;
import interfaces.IMovement;
import interfaces.IProcessing;
import utilities.GameData;
import utilities.Inputs;
import utilities.SPIlocator;
import utilities.Type;

import java.util.ArrayList;
import java.util.Collection;

public class EnemyMovement implements IProcessing {

    private AIMovement aiMovement;


    @Override
    public void process(ArrayList<Inputs> inputs, GameData gameData) {
        for (Entity enemy : gameData.getEntityList(Type.ENEMY)) {
            if (enemy.getType() == Type.ENEMY) {
                SPIlocator.getSpIlocator().getMovement().defaultMove(aiMovement.getInputs(gameData, enemy), enemy, gameData);
                enemy.getSprite().freshRotate(enemy.getRadians(), enemy.getPosition());
            }
        }
    }

    @Override
    public String toString(){
        return Type.ENEMY.toString();
    }
}
