module enemy {
    exports enemy;
    requires common;
    requires java.desktop;

    uses interfaces.IMovement;

    provides interfaces.IPlugin with enemy.EnemyPlugin;
    provides interfaces.IDrawable with enemy.EnemyPlugin;
    provides interfaces.IProcessing with enemy.EnemyMovement;
}