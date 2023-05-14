module grillBullet {
    exports grillBullet;
    requires common;
    requires java.desktop;

    provides interfaces.IBulletService with grillBullet.BulletPlugin;
    //provides interfaces.IProcessing with grillBullet.BulletControlSystem;
    provides interfaces.IDrawable with grillBullet.BulletPlugin;
}