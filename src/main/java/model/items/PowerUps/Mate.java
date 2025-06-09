package main.java.model.items.PowerUps;

import main.java.model.Player;
import main.java.model.PowerUpFactory.Power;

import java.awt.Graphics;

public class Mate extends PowerUp implements Power {

    private static final String NAME = "Mate";
    private static final String URL = "src\\main\\java\\model\\items\\PowerUps\\Mate.java";

    public Mate(int posX, int posY) {
        super(posX, posY, NAME, URL);
    }

    @Override
    public void take(Player player) {
        player.addSpeed(5);
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