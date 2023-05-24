import enemy.EnemyControl;

module enemy {
    exports enemy;
    exports enemy.aiMovement;
    requires common;
    requires java.desktop;

    uses interfaces.IMovement;

    provides interfaces.IPlugin with enemy.EnemyPlugin;
    provides interfaces.IDrawable with enemy.EnemyPlugin;
    provides interfaces.IProcessing with EnemyControl;
}