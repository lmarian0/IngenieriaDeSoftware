package main.java.controller;

import main.java.model.Player;
import main.java.model.constants.ScreenSettings;

public class Camera {

    private int offsetX;
    private int offsetY;
    private ScreenSettings screenSettings;

    public void update(Player player) {
        int screenWidth = screenSettings.getScaledWidth(screenSettings);
        int screenHeight = screenSettings.getScaledHeight(screenSettings);
        int worldWidth = screenSettings.getScreenCols();
        int worldHeight = screenSettings.getScreenRows();

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

