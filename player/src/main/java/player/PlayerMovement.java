package player;

import abstractClasses.Entity;
import interfaces.IProcessing;
import utilities.GameData;
import utilities.Inputs;
import utilities.SPIlocator;
import utilities.Type;

import java.util.ArrayList;

public class PlayerMovement implements IProcessing {
    private Entity player;
    private long lastShot = 0;

    private int processesPressed = 0;
    private int bulletsShot = 0;

    @Override
    public void process(ArrayList<Inputs> inputs, GameData gameData) {
        for (Entity player : gameData.getEntityList(Type.PLAYER)) {
            this.player = player;
        }
        if (player.equals(null)) {
            return;
        }

        if(player.getHealth() == 0){
            SPIlocator.getSpIlocator().getPluginMap().get(player.getType()).delete(gameData,player);
        }

        if (inputs.contains(Inputs.KEY_C)) {
            processesPressed++;
            if (processesPressed > 120) {
                bulletsShot = 0;
            }
            player.setPosition(SPIlocator.getSpIlocator().getMovement().defaultMove(new ArrayList<>(), player, gameData));
            player.getSprite().freshRotate(player.getRadians(), player.getPosition());
            return;
        }

        processesPressed = 0;
        if (inputs.contains(Inputs.KEY_SPACE) && bulletsShot < 4) {
            if (System.currentTimeMillis() - lastShot > 200) {
                bulletsShot++;
                lastShot = System.currentTimeMillis();
                gameData.addBullet(Type.BULLET, player);
            }
        }
        player.setPosition(SPIlocator.getSpIlocator().getMovement().defaultMove(inputs, player, gameData));
        player.getSprite().freshRotate(player.getRadians(), player.getPosition());


    }


    @Override
    public String toString() {
        return Type.PLAYER.toString();
    }
}

