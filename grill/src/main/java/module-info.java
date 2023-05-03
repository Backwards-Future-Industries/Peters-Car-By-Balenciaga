module PetersCar {
    exports weapon;
    requires common;
    requires java.desktop;

    provides interfaces.IPlugin with weapon.Bullet, weapon.Grill;
}