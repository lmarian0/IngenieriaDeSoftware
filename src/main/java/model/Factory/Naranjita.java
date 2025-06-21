package main.java.model.Factory;
import java.awt.Color;
import java.awt.Graphics;

import main.java.model.Enemy;
import main.java.model.constants.Constants;

public class Naranjita extends Enemy {
    private static String sU1 = "src\\main\\java\\view\\resources\\naranjita\\naranjita_up1.png";
    private static String sU2 = "src\\main\\java\\view\\resources\\naranjita\\naranjita_up2.png";
    private static String sD1 = "src\\main\\java\\view\\resources\\naranjita\\naranjita_down1.png";
    private static String sD2 = "src\\main\\java\\view\\resources\\naranjita\\naranjita_down2.png";
    private static String sL1 = "src\\main\\java\\view\\resources\\naranjita\\naranjita_left1.png";
    private static String sL2 = "src\\main\\java\\view\\resources\\naranjita\\naranjita_left2.png";
    private static String sR1 = "src\\main\\java\\view\\resources\\naranjita\\naranjita_right1.png";
    private static String sR2 = "src\\main\\java\\view\\resources\\naranjita\\naranjita_right2.png";
    
    public Naranjita(int posX, int posY) {
        super("Naranjita", 2, posX, posY, 50, 10, 800, 500, sU1, sU2, sD1, sD2, sL1, sL2, sR1, sR2);
    }

    @Override
    public void draw(Graphics g, int posX, int posY) {
        if (super.getCurrentSprite()!=null) {
            g.drawImage(super.getCurrentSprite(), getPosX(), getPosY(), getWidth()*Constants.SCALE.getSize(), getHeight()*Constants.SCALE.getSize(), null);
        }else{
            g.setColor(Color.ORANGE);
            g.fillRect(getPosX(), getPosY(), 50, 50); // Dibujar un cuadrado verde como representación del goblin
        }
    }

}
