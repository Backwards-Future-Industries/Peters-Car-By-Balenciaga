module player {
    exports player;
    requires common;
    requires java.desktop;

    provides interfaces.IPlugin with player.PlayerPlugin;
    provides abstractClasses.Entity with player.PlayerPlugin;
}