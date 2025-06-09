package main.java.view.ui;

import main.java.model.observer.Observer;
import main.java.model.Player;

import java.awt.*;

public class XPBar implements Observer {
    private Player player;
    private int currentXP;

    public XPBar(Player player) {
        this.player = player;
        this.currentXP = player.getXp();
        player.addObserver(this);
    }

    @Override
    public void update() {
        this.currentXP = player.getXp();
    }

    public void draw(Graphics g) {
        int maxXp = 100; // o el umbral para el siguiente nivel si lo hacés dinámico
        int currentXp = Math.max(0, player.getXp());
        int barWidth = Math.min(currentXp, maxXp);  // Limita visualmente si se pasa

        float xpRatio = currentXp / (float) maxXp;

        // Cambiar color por progreso
        if (xpRatio <= 0.33f) {
            g.setColor(Color.CYAN);
        } else if (xpRatio <= 0.66f) {
            g.setColor(new Color(255, 105, 180));  // Rosa (hot pink)
        } else {
            g.setColor(new Color(128, 0, 128));    // Morado
        }

        g.fillRect(20, 50, barWidth, 15);

        // Marco negro
        g.setColor(Color.BLACK);
        g.drawRect(20, 50, maxXp, 15);

        // Borde y texto
        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        g.setColor(Color.BLACK);
        g.drawString("XP: " + currentXp, 25, 62);

    }

}