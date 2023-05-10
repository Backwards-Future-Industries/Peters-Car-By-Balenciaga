module enemy {
    exports enemy;
    requires common;
    requires java.desktop;

    provides interfaces.IPlugin with enemy.EnemyPlugin;
    provides abstractClasses.Entity with enemy.EnemyPlugin;
    provides interfaces.IDrawable with enemy.EnemyPlugin;
    provides interfaces.IProcessing with enemy.EnemyPlugin;
}