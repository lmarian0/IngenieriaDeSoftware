package main.java.view.ui;

import main.java.model.gameState.Observer;
import main.java.model.Player;

import java.awt.*;

public class HealthBar implements Observer {
    private Player player;
    private int currentHp;

    public HealthBar(Player player) {
        this.player = player;
        this.currentHp = player.getHp();
        player.addObserver(this);
    }

    @Override
    public void update() {
        this.currentHp = player.getHp();
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(20, 20, Math.max(0, currentHp) * 2, 20); // Escala: 1 HP = 2px
        g.setColor(Color.WHITE);
        g.drawString("HP: " + currentHp, 25, 35);
    }
}
