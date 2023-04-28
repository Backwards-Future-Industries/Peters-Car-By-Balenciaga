module enemy {
    exports enemy;
    requires common;
    requires java.desktop;

    provides interfaces.IPlugin with enemy.EnemyPlugin;
}