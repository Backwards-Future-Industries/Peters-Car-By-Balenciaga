import utilities.SPIlocator;

module core {
    requires player;
    requires gameEngine;
    requires common;

    uses interfaces.IPlugin;
    uses abstractClasses.Entity;
    uses SPIlocator;
}