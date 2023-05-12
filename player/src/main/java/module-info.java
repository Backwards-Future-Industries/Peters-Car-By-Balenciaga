module player {
    exports player;
    requires common;
    requires java.desktop;

    uses interfaces.IMovement;
    uses interfaces.IDrawable;
    uses interfaces.IBulletService;
    uses interfaces.IProcessing;

    provides interfaces.IPlugin with player.PlayerPlugin;
    provides interfaces.IDrawable with player.PlayerPlugin;
    provides interfaces.IProcessing with player.PlayerMovement;
}