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
        g.setColor(Color.BLUE);
        g.fillRect(player.getPosX(), player.getPosY(), 32, 32);

        g.setColor(Color.RED);
        for (Enemy enemy : controller.getEnemies()) {
            g.fillRect(enemy.getPosX(), enemy.getPosY(), 32, 32);
        }
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
