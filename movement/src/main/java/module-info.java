module movement {
    exports movement;
    requires common;
    requires java.desktop;

    uses interfaces.IMovement;

    provides interfaces.IMovement with movement.DefaultMovement;
}