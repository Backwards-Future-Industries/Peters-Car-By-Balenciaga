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
            this.entity = enemy;
        }
        if (this.entity.equals(null)){
            return;
        }

        aiMovement = new AIMovement(gameData, this.entity);
        SPIlocator.getSpIlocator().getMovement().defaultMove(aiMovement.getInputsBasedOnAStar(), this.entity, gameData);
        this.entity.getSprite().freshRotate(this.entity.getRadians(), this.entity.getPosition());
    }

    @Override
    public String toString() {
        return Type.ENEMY.toString();
    }
}
