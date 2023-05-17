module Collision {
    exports collision;
    requires common;
    requires java.desktop;

    provides interfaces.IProcessing with collision.CollisionDetection;
}