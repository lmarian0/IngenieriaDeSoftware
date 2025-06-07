package main.java.view.ui;

import main.java.model.observer.Observer;
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
        int maxHp = 100; // o podrías pasarlo como parámetro si es dinámico
        int currentHp = Math.max(0, player.getHp());
        int barWidth = currentHp * 2;

        // Cambiar color según % de vida
        float hpRatio = currentHp / (float) maxHp;
        if (hpRatio > 0.6f) {
            g.setColor(Color.GREEN);
        } else if (hpRatio > 0.3f) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.RED);
        }

        // Dibujar barra
        g.fillRect(20, 20, barWidth, 20);

        // Marco negro
        g.setColor(Color.BLACK);
        g.drawRect(20, 20, maxHp * 2, 20);

        // Establecer fuente en negrita y tamaño 14
        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        g.setColor(Color.BLACK);  // color del texto
        g.drawString("HP: " + currentHp, 25, 35);

    }

}
