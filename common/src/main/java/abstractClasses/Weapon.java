package abstractClasses;

import utilities.Types;

import java.net.URL;

public abstract class Weapon extends Entity {
    public Weapon(int health, URL sprite) {
        super(health, sprite, Types.WEAPON,new double[]{0,1});
    }
    public abstract void shoot();
}
