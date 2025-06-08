package main.java.model.Factory;
import java.awt.Color;
import java.awt.Graphics;

import main.java.model.Enemy;

public class Goblin extends Enemy {

    public Goblin(int posX, int posY) {
        super("Goblin", 3, posX, posY, 50, 1, 100, 500, null);
    }

    @Override
    public void draw(Graphics g, int posX, int posY) {
        g.setColor(Color.GREEN);
        g.fillRect(getPosX(), getPosY(), 50, 50); // Dibujar un cuadrado verde como representación del goblin
    }
    
}
