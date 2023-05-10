module player {
    exports player;
    requires common;
    requires java.desktop;


    provides interfaces.IPlugin with player.PlayerPlugin;
    provides interfaces.IDrawable with player.PlayerPlugin;
    provides interfaces.IProcessing with player.PlayerMovement;
}