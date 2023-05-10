import abstractClasses.Entity;
import utilities.Types;

import java.net.URL;

public class concreteEntity extends Entity {

    public concreteEntity(int health, URL sprite) {
        super(health, sprite, Types.UNDEFINED);
    }
}
