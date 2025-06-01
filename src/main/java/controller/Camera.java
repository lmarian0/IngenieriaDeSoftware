package main.java.controller;

import main.java.model.Player;
import main.java.constants.MapConstants;

public class Camera {

    private int offsetX;
    private int offsetY;

    public void update(Player player) {
        int screenWidth = MapConstants.SCREEN_WIDTH;
        int screenHeight = MapConstants.SCREEN_HEIGHT;
        int worldWidth = MapConstants.MAP_WIDTH * MapConstants.TILE_SIZE;
        int worldHeight = MapConstants.MAP_HEIGHT * MapConstants.TILE_SIZE;

        // Centrar al jugador
        offsetX = player.getPosX() - screenWidth / 2 + player.getWidth() / 2;
        offsetY = player.getPosY() - screenHeight / 2 + player.getHeight() / 2;

        // Limitar los bordes
        offsetX = Math.max(0, Math.min(offsetX, worldWidth - screenWidth));
        offsetY = Math.max(0, Math.min(offsetY, worldHeight - screenHeight));
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }
}

