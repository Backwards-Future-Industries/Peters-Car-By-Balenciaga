module bullet {
    exports bullet;
    requires common;
    requires java.desktop;

    provides interfaces.IBulletService with bullet.BulletPlugin;
    provides interfaces.IProcessing with bullet.BulletControlSystem;
    provides interfaces.IDrawable with bullet.BulletPlugin;
}