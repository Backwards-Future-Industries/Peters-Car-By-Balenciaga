package interfaces;

import abstractClasses.Entity;
import utilities.Inputs;
import utilities.Vector2D;

import java.util.ArrayList;

public interface IMovement {
    default int[] defaultMove(ArrayList<Inputs> inputs, Entity entity) {
        double dx = entity.getDirection().getX();
        double dy = entity.getDirection().getY();
        double rotationSpeed = Math.PI * ((double) 1 / 32);

        double radians = entity.getRadians();
        int[] newPosition = new int[2];


        //Accelerate
        if (inputs.contains(Inputs.KEY_W)) {
            dx += Math.cos(radians) * entity.getAcceleration();
            dy += Math.sin(radians) * entity.getAcceleration();
        }

        //De-accelerate
        if (inputs.contains(Inputs.KEY_S)) {
            dx -= Math.cos(radians) * entity.getAcceleration()/2;
            dy -= Math.sin(radians) * entity.getAcceleration()/2;
        }

        //Make sure speed is less than MaxSpeed!!!
        double speed = Math.sqrt(dx * dx + dy * dy);
        if (speed > entity.getMaxSpeed()) {
            dx = (dx / speed) * entity.getMaxSpeed();
            dy = (dy / speed) * entity.getMaxSpeed();
        }


        //Makes sure that when radians passes 2pi its reset to 0 removing potential overflow
        if (Math.abs(radians) > 2*Math.PI) {
            radians = 0;
        }

        //Using the rotation matrix to rotate the direction vector counter-clockwise and updating its radians
        if (inputs.contains(Inputs.KEY_A)) {
            dx = dx*Math.cos(-rotationSpeed)-dy*Math.sin(-rotationSpeed);
            dy = dx*Math.sin(-rotationSpeed)+dy*Math.cos(-rotationSpeed);
            radians -= rotationSpeed;
        }

        //Using the rotation matrix to rotate the direction vector clockwise and updating its radians
        if (inputs.contains(Inputs.KEY_D)) {
            dx = dx*Math.cos(rotationSpeed)-dy*Math.sin(rotationSpeed);
            dy = dx*Math.sin(rotationSpeed)+dy*Math.cos(rotationSpeed);
            radians += rotationSpeed;
        }

        System.out.println("dx: "+dx);
        System.out.println("dy: "+dy);
        System.out.println("Speed: "+speed);


        // SOMEWHERE IN THIS CODE THE CAR LOSES MOMENTUM PURELY BECAUSE ITS ROTATING WHICH IS MOST LIKELY DUE TO SOME DIGITS BEING LOST DUE TO TYPECASTING :((((((
        entity.setRadians(radians);
        entity.setDirection(new Vector2D(dx, dy));

        newPosition[0] = (int) (entity.getPosition()[0] + Math.round(dx));
        newPosition[1] = (int) (entity.getPosition()[1] + Math.round(dy));

        entity.setPosition(newPosition);

        return newPosition;
    }
}
