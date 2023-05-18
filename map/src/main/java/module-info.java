module map {
    exports map;
    requires common;
    requires java.desktop;

    provides interfaces.IMapService with map.Map;
    provides interfaces.IDrawable with map.Map;
}