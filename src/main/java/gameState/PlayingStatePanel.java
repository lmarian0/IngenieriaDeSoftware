package main.java.gameState;

import main.java.model.Enemy;
import main.java.model.Player;

import javax.swing.*;
import java.awt.*;

public class PlayingStatePanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Player player = controller.getPlayer();

        // Centramos el jugador en el medio de la pantalla
        int offsetX = player.getPosX() - SCREENWIDTH / 2 + player.getWidth() / 2;
        int offsetY = player.getPosY() - SCREENHEIGHT / 2 + player.getHeight() / 2;

        // Dibujar enemigos
        g.setColor(Color.BLUE);
        for (Enemy enemy : controller.getEnemies()) {
            g.fillRect(enemy.getPosX() - offsetX, enemy.getPosY() - offsetY, enemy.getWidth(), enemy.getHeight());
        }

        // Dibujar jugador
        g.setColor(Color.RED);
        g.fillRect(player.getPosX() - offsetX, player.getPosY() - offsetY, player.getWidth(), player.getHeight());
    }
}
