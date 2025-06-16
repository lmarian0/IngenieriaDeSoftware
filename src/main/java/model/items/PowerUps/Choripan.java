package main.java.model.items.PowerUps;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.java.model.Player;
import main.java.model.PowerUpFactory.Power;

public class Choripan extends PowerUp implements Power {
    private BufferedImage image;
    private static final String NAME = "Choripan";
    private static final String URL = "src\\main\\java\\view\\resources\\Sprites\\Choripan.png";

    public Choripan(int posX, int posY) {
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
        if(player.getHp()<80){
            player.heal(20);
        }else if(player.getHp()<100){
            player.heal(100-player.getHp());
        }
        System.out.println("Choripan taken! Health increased by 20.");
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
