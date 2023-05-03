import interfaces.IDrawable;
import utilities.SPIlocator;

module core {
    requires player;
    requires gameEngine;
    requires common;
    requires map;

    uses interfaces.IPlugin;
    uses abstractClasses.Entity;
    uses SPIlocator;
    uses IDrawable;
}