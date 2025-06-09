package main.java.model.PowerUpFactory;

import java.awt.Graphics;

import main.java.model.Player;

public interface Power {

    public void take(Player player);
    
    public void draw(Graphics g);

    public boolean active(Player player);
}
