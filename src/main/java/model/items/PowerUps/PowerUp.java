package main.java.model.items.PowerUps;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.java.model.Player;
import main.java.model.items.Drop;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PowerUp extends Drop{
    private BufferedImage image;
    private String name;

    public PowerUp(int posX, int posY, String name, String imagePath) {
        super(posX, posY);
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            this.image = null; // Si no se puede cargar la imagen, se establece como null
            e.printStackTrace();
        }
    }

    public boolean active(Player player){
        int dx = Math.abs(getPosX() - player.getPosX());
        int dy = Math.abs(getPosY() - player.getPosY());
        return dx < player.getWidth() && dy < player.getHeight();
    }

    public String getName() {
        return name;
    }

    public int getWidth(){return 20;}
    public int getHeight(){return 20;}

    public void draw (Graphics g){
       if (image != null) {
            g.drawImage(image, super.getPosX(), super.getPosY(), getWidth(), getHeight(), null);
        } else {
            g.setColor(Color.PINK);
            g.fillRect(super.getPosX(), super.getPosY(), getWidth(), getHeight());
        }
    }


}
