package main.java.model.items.PowerUps;

import java.awt.Graphics;

import main.java.model.Player;
import main.java.model.PowerUpFactory.Power;

public class Fernet extends PowerUp implements Power {

    private static final String NAME = "Fernet";
    private static final String URL = "src\\main\\java\\model\\items\\PowerUps\\Fernet.java";

    public Fernet(int posX, int posY) {
        super(posX, posY, NAME, URL);
    }

    @Override
    public void take(Player player) {
        player.addSpeed(20);
        System.out.println("Fernet taken! Health increased by 20.");
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