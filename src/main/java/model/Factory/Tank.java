package main.java.model.Factory;

import main.java.model.Enemy;
import main.java.model.constants.Constants;

import java.awt.*;

public class Tank extends Enemy {
    private static String sU1 = "src\\main\\java\\view\\resources\\Rappi\\rappi_up1.png";
    private static String sU2 = "src\\main\\java\\view\\resources\\Rappi\\rappi_up1.png";
    private static String sD1 = "src\\main\\java\\view\\resources\\Rappi\\rappi_down1.png";
    private static String sD2 = "src\\main\\java\\view\\resources\\Rappi\\rappi_down2.png";
    private static String sL1 = "src\\main\\java\\view\\resources\\Rappi\\rappi_left1.png";
    private static String sL2 = "src\\main\\java\\view\\resources\\Rappi\\rappi_left2.png";
    private static String sR1 = "src\\main\\java\\view\\resources\\Rappi\\rappi_right1.png";
    private static String sR2 = "src\\main\\java\\view\\resources\\Rappi\\rappi_right2.png";

    public Tank(int posX, int posY) {
        super("Tank", 2, posX, posY, 200, 40, 1000, 500, sU1, sU2, sD1, sD2, sL1, sL2, sR1, sR2);
    }

    @Override
    public void draw(Graphics g, int posX, int posY) {
        if (super.getCurrentSprite()!=null) {
            g.drawImage(super.getCurrentSprite(), getPosX(), getPosY(), getWidth()*Constants.SCALE.getSize(), getHeight()*Constants.SCALE.getSize(), null);
        }else{
            g.setColor(Color.RED);
            g.fillRect(getPosX(), getPosY(), 50, 50); // Dibujar un cuadrado verde como representación del goblin
        }
    }
}
