module grill {
    exports grill;
    requires common;
    requires java.desktop;

    provides interfaces.IPlugin with grill.Grill;
}