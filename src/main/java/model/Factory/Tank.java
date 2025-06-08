package main.java.model.Factory;

import main.java.model.Enemy;

import java.awt.*;

public class Tank extends Enemy {

    public Tank(int posX, int posY) {
        super("Tank", 1, posX, posY, 200, 40, 1000, 500, null);
    }

    @Override
    public void draw(Graphics g, int posX, int posY) {
        g.setColor(Color.RED);
        g.fillRect(getPosX(), getPosY(), 50, 50); // Dibujar un cuadrado verde como representación del goblin
    }
}
