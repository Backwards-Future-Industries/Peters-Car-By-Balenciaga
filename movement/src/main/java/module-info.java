module movement {
    exports movement;
    requires common;
    requires java.desktop;

    provides interfaces.IMovement with movement.DefaultMovement;
}