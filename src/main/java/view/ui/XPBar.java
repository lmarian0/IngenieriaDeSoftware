package main.java.view.ui;

import main.java.model.gameState.Observer;
import main.java.model.Player;

import java.awt.*;

public class XPBar implements Observer {
    private Player player;
    private int currentXP;

    public XPBar(Player player) {
        this.player = player;
        this.currentXP = player.getXP();
        player.addObserver(this);
    }

    @Override
    public void update() {
        this.currentXP = player.getXP();
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(20, 50, currentXP, 15); // Escala a gusto
        g.setColor(Color.WHITE);
        g.drawString("XP: " + currentXP, 25, 62);
    }
}