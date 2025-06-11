package main.java.model.Factory;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;

import javax.imageio.ImageIO;

import main.java.model.Enemy;

public class Naranjita extends Enemy {

    private BufferedImage image, spriteLeft1, spriteLeft2;
    
    public Naranjita(int posX, int posY) {
        super("Naranjita", 2, posX, posY, 50, 10, 800, 500, null);
        try {
            spriteLeft1 = ImageIO.read(new File("src\\main\\java\\view\\resources\\naranjita\\naranjita_1.png"));
            spriteLeft2 = ImageIO.read(new File("src\\main\\java\\view\\resources\\naranjita\\naranjita_2.png"));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    }

    @Override
    public void draw(Graphics g, int posX, int posY) {
        g.setColor(Color.ORANGE);
        g.fillRect(getPosX(), getPosY(), 50, 50); // Dibujar un cuadrado verde como representación del goblin
    }

}
