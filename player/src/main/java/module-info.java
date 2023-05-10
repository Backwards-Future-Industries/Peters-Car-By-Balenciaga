module player {
    exports player;
    requires common;
    requires java.desktop;

    uses interfaces.IProcessing;
    uses interfaces.IPlugin;
    uses interfaces.IDrawable;

    provides interfaces.IPlugin with player.PlayerPlugin;
    provides interfaces.IDrawable with player.PlayerPlugin;
    provides interfaces.IProcessing with player.PlayerMovement;
}