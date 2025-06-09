package main.java.model.items.PowerUps;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.java.model.Player;
import main.java.model.PowerUpFactory.Power;

public class Mate extends PowerUp implements Power {
    private BufferedImage image;
    private static final String NAME = "Mate";
    private static final String URL = "src\\main\\java\\view\\resources\\Sprites\\mateSprite.png";

    public Mate(int posX, int posY) {
        super(posX, posY, NAME, URL);
        try {
            this.image = ImageIO.read(new File(URL));
        } catch (IOException e) {
            this.image = null; // Si no se puede cargar la imagen, se establece como null
            e.printStackTrace();
        }
    }

    @Override
    public void take(Player player) {
        if(player.getMovSpeed()<60){
            player.addSpeed(5);
        }
        System.out.println("Mate taken! Health increased by 20.");
    }

    @Override
    public void draw (Graphics g){
       super.draw(g);
    }

    @Override
    public boolean active(Player player){
        return super.active(player);
    }
    
}