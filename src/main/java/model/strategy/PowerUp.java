package main.java.model.strategy;
import  main.java.model.Player;

import java.awt.image.BufferedImage;

public abstract class PowerUp {

    private BufferedImage image;
    private int posX, posY;

    /*
    public PowerUp(BufferedImage image, int posX, int posY) {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
    }
     */

    public abstract void takePowerUp(Player player);
}
