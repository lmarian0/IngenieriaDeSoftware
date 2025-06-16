package main.java.model.Factory;
import java.awt.Color;
import java.awt.Graphics;

import main.java.model.Enemy;
import main.java.model.constants.Constants;

public class Goblin extends Enemy {
    private static String sU1 = "src\\main\\java\\view\\resources\\Carpincho\\carpincho_up1.png";
    private static String sU2 = "src\\main\\java\\view\\resources\\Carpincho\\carpincho_up2.png";
    private static String sD1 = "src\\main\\java\\view\\resources\\Carpincho\\carpincho_down1.png";
    private static String sD2 = "src\\main\\java\\view\\resources\\Carpincho\\carpincho_down2.png";
    private static String sL1 = "src\\main\\java\\view\\resources\\Carpincho\\carpincho_left1.png";
    private static String sL2 = "src\\main\\java\\view\\resources\\Carpincho\\carpincho_left2.png";
    private static String sR1 = "src\\main\\java\\view\\resources\\Carpincho\\carpincho_right1.png";
    private static String sR2 = "src\\main\\java\\view\\resources\\Carpincho\\carpincho_right2.png";

    public Goblin(int posX, int posY) {
        super("Goblin", 5, posX, posY, 50, 1, 50, 500, sU1, sU2, sD1, sD2, sL1, sL2, sR1, sR2);
    }

    @Override
    public void draw(Graphics g, int posX, int posY) {
        if (super.getCurrentSprite()!=null) {
            g.drawImage(super.getCurrentSprite(), getPosX(), getPosY(), getWidth()*Constants.SCALE.getSize(), getHeight()*Constants.SCALE.getSize(), null);
        }else{
            g.setColor(Color.GREEN);
            g.fillRect(getPosX(), getPosY(), 50, 50); // Dibujar un cuadrado verde como representación del goblin
        }
    }
    
}
