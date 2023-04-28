package interfaces;

import abstractClasses.Entity;
import utilities.Inputs;
import utilities.Vector2D;

import java.util.ArrayList;

public interface IMovement {
    default int[] defaultMove(ArrayList<Inputs> inputs, Entity entity, IGameEngine gameEngine) {
        Vector2D direction = entity.getDirection();
        double acceleration = entity.getAcceleration();
        double radians = entity.getRadians();
        int[] newPosition = new int[2];
        double dt = (double) gameEngine.getDeltaProcessTime() / (double) 1000;

        if (inputs.contains(Inputs.KEY_W)) accelerate(acceleration, direction, radians, dt);
        if (inputs.contains(Inputs.KEY_S)) deAccelerate(acceleration, direction, radians, dt);
        if (inputs.contains(Inputs.KEY_A)) radians = rotate(direction, radians, -1, dt);
        if (inputs.contains(Inputs.KEY_D)) radians = rotate(direction, radians, 1, dt);

        //Prevents radians from being negative (Completely unnecessary but looks nice :))
        if (radians < 0) {
            radians += 2 * Math.PI;
        }

        //Makes sure that when radians passes 2pi its reset to 0 removing potential overflow
        if (Math.abs(radians) > 2 * Math.PI) {
            radians -= 2 * Math.PI;
        }



        //Make sure speed is less than MaxSpeed. By calculating the unit for the direction and the extending it with the max speed and save it as the new direction vector
        double speed = Math.sqrt(Math.pow(direction.getX(), 2) + Math.pow(direction.getY(), 2));
        if (speed > entity.getMaxSpeed()) {
            direction.setX((direction.getX() / speed) * entity.getMaxSpeed());
            direction.setY((direction.getY() / speed) * entity.getMaxSpeed());
        }

        // SOMEWHERE IN THIS CODE THE CAR LOSES MOMENTUM PURELY BECAUSE ITS ROTATING WHICH IS MOST LIKELY DUE TO SOME DIGITS BEING LOST DUE TO TYPECASTING :((((((
        entity.setRadians(radians);
        entity.setDirection(direction);

        newPosition[0] = (int) (entity.getPosition()[0] + Math.round(direction.getX()));
        newPosition[1] = (int) (entity.getPosition()[1] + Math.round(direction.getY()));

        entity.setPosition(newPosition);

        System.out.println(radians);
        return newPosition;
    }

    private void accelerate(double acceleration, Vector2D direction, double radians, double dt) {
        direction.setX(direction.getX() + Math.cos(radians) * (acceleration * dt));
        direction.setY(direction.getY() + Math.sin(radians) * (acceleration * dt));
    }

    private void deAccelerate(double acceleration, Vector2D direction, double radians, double dt) {
        direction.setX(direction.getX() - Math.cos(radians) * ((acceleration / 2) * dt));
        direction.setY(direction.getY() - Math.sin(radians) * ((acceleration / 2) * dt));
    }

    //Using the rotation matrix to rotate the direction vector and updating its radians
    private double rotate(Vector2D direction, double radians, int rotationDirection, double dt) {
        double rotationSpeed = Math.PI * ((double) 1 / 32);
        double sin = Math.sin(rotationDirection * rotationSpeed);
        double cos = Math.cos(rotationDirection * rotationSpeed);
        direction.setX(direction.getX() * cos - direction.getY() * sin);
        direction.setY(direction.getX() * sin + direction.getY() * cos);
        radians += rotationDirection * rotationSpeed;
        return radians;
    }
}
