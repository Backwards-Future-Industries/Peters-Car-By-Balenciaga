module common {
    requires java.desktop;
    exports abstractClasses;
    exports interfaces;
    exports utilities;
    exports utilities.image;

    uses interfaces.IPlugin;
    uses interfaces.IDrawable;
    uses interfaces.IProcessing;
    uses interfaces.IMovement;
    uses interfaces.IBulletService;
}