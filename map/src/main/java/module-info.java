module map {
    exports map;
    requires common;
    requires java.desktop;

    provides interfaces.IPlugin with map.Map;
    provides interfaces.IDrawable with map.Map;
}