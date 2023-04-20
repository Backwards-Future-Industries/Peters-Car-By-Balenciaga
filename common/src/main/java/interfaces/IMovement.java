package interfaces;

import abstractClasses.Entity;
import utilities.Inputs;
import utilities.Vector2D;

import java.util.ArrayList;

public interface IMovement {
    default int[] defaultMove(ArrayList<Inputs> inputs, Entity entity) {
        double dx = entity.getDirection().getX();
        double dy = entity.getDirection().getY();

        double radians = entity.getRadians();
        int[] newPosition = new int[2];


        //Accelerate
        if (inputs.contains(Inputs.KEY_W)) {
            dx += Math.cos(radians) * entity.getAcceleration();
            dy += Math.sin(radians) * entity.getAcceleration();
        }

        //De-accelerate
        if (inputs.contains(Inputs.KEY_S)) {
            dx -= Math.cos(radians) * entity.getAcceleration();
            dy -= Math.sin(radians) * entity.getAcceleration();
        }

        //Make sure speed is less than MaxSpeed!!!
        double speed = Math.sqrt(dx * dx + dy * dy);
        if (speed > entity.getMaxSpeed()) {
            dx = (dx / speed) * entity.getMaxSpeed();
            dy = (dy / speed) * entity.getMaxSpeed();
        }

        //Turning
        if (inputs.contains(Inputs.KEY_A)) {
            radians -= Math.PI * ((double) 1 / 32);
        }

        if (inputs.contains(Inputs.KEY_D)) {
            radians += Math.PI * ((double) 1 / 32);
        }

        if (Math.round(radians%(2*Math.PI)*1000)/1000 == 0) {
            radians=0;
        }

        entity.setRadians(radians);
        entity.setDirection(new Vector2D(Math.cos(radians)*speed, Math.sin(radians)*speed));

        newPosition[0] += entity.getPosition()[0]+ dx;
        newPosition[1] += entity.getPosition()[1]+ dy;

        entity.setPosition(newPosition);

        speed = Math.sqrt(dx * dx + dy * dy);

        System.out.println("radians: " + radians);
        System.out.println("Speed: "+ speed);


        return newPosition;
    }
}
