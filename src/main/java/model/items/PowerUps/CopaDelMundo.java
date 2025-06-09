package main.java.model.items.PowerUps;

import java.awt.Graphics;

import main.java.model.Player;
import main.java.model.PowerUpFactory.Power;

public class CopaDelMundo extends PowerUp implements Power {

    private static final String NAME = "Copa del mundo";
    private static final String URL = "src\\main\\java\\model\\items\\PowerUps\\copaDoMundoSprite.java";

    public CopaDelMundo(int posX, int posY) {
        super(posX, posY, NAME, URL);
    }

    @Override
    public void take(Player player) {
    
        player.addDamage(5);
        System.out.println("Copa del mundo taken! Health increased by 20.");
    
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
