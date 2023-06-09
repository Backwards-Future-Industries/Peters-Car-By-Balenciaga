package movement;

import abstractClasses.Entity;
import interfaces.IMovement;
import utilities.GameData;
import utilities.Inputs;
import utilities.Vector2D;

import java.awt.*;
import java.util.ArrayList;

public class DefaultMovement implements IMovement {
    public Point defaultMove(ArrayList<Inputs> inputs, Entity entity, GameData gameData) {
        Vector2D direction = entity.getDirection();
        double acceleration = entity.getAcceleration();
        double radians = entity.getRadians();
        double maxSpeed = entity.getMaxSpeed();
        Point newPosition = new Point(0, 0);

        if (inputs.contains(Inputs.KEY_W)) accelerate(acceleration, direction, radians);
        if (inputs.contains(Inputs.KEY_S)) deAccelerate(acceleration, direction, radians);
        if (inputs.contains(Inputs.KEY_A)) radians = rotate(direction, radians, -1, maxSpeed);
        if (inputs.contains(Inputs.KEY_D)) radians = rotate(direction, radians, 1, maxSpeed);

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

        newPosition.x = (int) (entity.getPosition().x + Math.round(direction.getX()));
        newPosition.y = (int) (entity.getPosition().y + Math.round(direction.getY()));

        if (newPosition.x > gameData.getScreenSize().width - entity.getSprite().getImage().getWidth())
            newPosition.x = gameData.getScreenSize().width - entity.getSprite().getImage().getWidth();
        if (newPosition.x < 0) newPosition.x = 0;
        if (newPosition.y > gameData.getScreenSize().height - entity.getSprite().getImage().getHeight())
            newPosition.y = gameData.getScreenSize().height - entity.getSprite().getImage().getHeight();
        if (newPosition.y < 0) newPosition.y = 0;

        entity.setPosition(newPosition);

        return newPosition;
    }

    private void accelerate(double acceleration, Vector2D direction, double radians) {
        direction.setX(direction.getX() + Math.cos(radians) * acceleration);
        direction.setY(direction.getY() + Math.sin(radians) * acceleration);
    }

    private void deAccelerate(double acceleration, Vector2D direction, double radians) {
        direction.setX(direction.getX() - Math.cos(radians) * (acceleration / 2));
        direction.setY(direction.getY() - Math.sin(radians) * (acceleration / 2));
    }

    //Using the rotation matrix to rotate the direction vector and updating its radians
    private double rotate(Vector2D direction, double radians, int rotationDirection, double maxSpeed) {
        double rotationSpeed = (Math.PI * ((double) 1 / 32)) * (direction.getLength() / maxSpeed);
        double sin = Math.sin(rotationDirection * rotationSpeed);
        double cos = Math.cos(rotationDirection * rotationSpeed);
        direction.setX(direction.getX() * cos - direction.getY() * sin);
        direction.setY(direction.getX() * sin + direction.getY() * cos);
        radians += rotationDirection * rotationSpeed;
        return radians;
    }
}
