import abstractClasses.Entity;
import utilities.Type;

import java.net.URL;

public class concreteEntity extends Entity {

    public concreteEntity(int health, URL sprite) {
        super(health, sprite, Type.UNDEFINED);
    }
}
