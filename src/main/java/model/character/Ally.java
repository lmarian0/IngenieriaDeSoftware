package main.java.model.character;

import java.awt.*;
import java.util.Random;

public class Ally extends NPC{
    private String dialogue;
    private Image image;
    private int clkDuration;

    public Ally(String name, String dialogue) {
        super(name, 0, 0, 0);
        this.dialogue = dialogue;
        this.clkDuration = 0;
    }



    //Setters
    public void setDialogue(String str){
        this.dialogue = str;
    }

    //Getters
    public String speak(){
        return dialogue;
    }

    public String getName(){
        return super.getName();
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

    public int getClkDuration() {
        return clkDuration;
    }

    public int getWidth() { return 64; }
    public int getHeight() { return 64; }

}
