package interfaces;

import utilities.Vector2D;

public interface IMovement {

    default Vector2D defaultMove() throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }
}
