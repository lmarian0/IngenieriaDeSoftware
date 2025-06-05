package main.java.view.ui;

import java.awt.*;
import main.java.model.Player;

public class HUD {
    private HealthBar healthBar;
    private XPBar xpBar;

    public HUD(Player player) {
        this.healthBar = new HealthBar(player);
        this.xpBar = new XPBar(player);
    }

    public void draw(Graphics g) {
        healthBar.draw(g);
        xpBar.draw(g);
    }
}