package main.java.model.Factory;
import java.awt.Color;
import java.awt.Graphics;

import main.java.model.Enemy;

public class Naranjita extends Enemy {

    public Naranjita(int posX, int posY) {
        super("Naranjita", 1, posX, posY, 50, 10, 1000, 500, null);
    }

    @Override
    public void draw(Graphics g, int posX, int posY) {
        g.setColor(Color.ORANGE);
        g.fillRect(getPosX(), getPosY(), 50, 50); // Dibujar un cuadrado verde como representación del goblin
    }

}
