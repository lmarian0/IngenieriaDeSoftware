package main.java.model.character;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ally extends NPC{
    private String dialogue;
    private BufferedImage image;
    private int clkDuration;

    public Ally(String name, String dialogue, String imagePath) {
        super(name, 0, 0, 0);
        this.dialogue = dialogue;
        this.clkDuration = 0;
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            this.image = null; // Si no se puede cargar la imagen, se establece como null
            e.printStackTrace();
        }
    }



    //Setters
    public void setDialogue(String str){
        this.dialogue = str;
    }

    public void draw (Graphics g, int offsetX, int offsetY){
       if (image != null) {
            g.drawImage(image, offsetX, offsetY, getWidth(), getHeight(), null);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(offsetX, offsetY, getWidth(), getHeight());
        }
    }

    public void addClkDuracion(){
        this.clkDuration++;
    }

    //Getters
    public String speak(){
        return dialogue;
    }

    public String getName(){
        return super.getName();
    }
    

    public int getClkDuration() {
        return clkDuration;
    }

    public int getWidth() { return 64; }
    public int getHeight() { return 64; }

}
