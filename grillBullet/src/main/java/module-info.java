module grillBullet {
    exports grillBullet;
    requires common;
    requires java.desktop;

    provides interfaces.IPlugin with grillBullet.Bullet;
}