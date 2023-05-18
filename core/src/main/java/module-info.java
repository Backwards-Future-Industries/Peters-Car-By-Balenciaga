

module core {
    requires gameEngine;
    requires common;
    requires ShadedLibGDX;
    requires java.desktop;

    uses interfaces.IPlugin;
    uses interfaces.IProcessing;
    uses interfaces.IDrawable;
}