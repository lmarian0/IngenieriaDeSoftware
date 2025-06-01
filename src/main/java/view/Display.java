package main.java.view;

import main.java.controller.Controller;
import main.java.model.Enemy;
import main.java.model.Player;
import main.java.constants.Constants;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {

    //SCREEN SETTINGS
    private final int FPS = 60;
    private final int TILESIZE = Constants.TILE_SIZE.getSize() * Constants.SCALE.getSize(); // 96x96 Tile Size
    private final int MAXSCREENCOL = 8;
    private final int MAXSCREENROW = 6;
    private final int SCREENWIDTH = TILESIZE * MAXSCREENCOL;
    private final int SCREENHEIGHT = TILESIZE * MAXSCREENROW;
    private final Controller controller;

    public Display(Controller controller) {
        this.controller = controller;
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

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



    /*
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        enzito.draw(g2d);
        g2d.dispose();
    }
     */

    public int getTILESIZE() {
        return TILESIZE;
    }
}
