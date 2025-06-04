package main.java.view;

import main.java.controller.Controller;
import main.java.map.GameMap;
import main.java.model.Enemy;
import main.java.model.Player;
import main.java.constants.Constants;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {

    // SCREEN SETTINGS
    private final int FPS = 60;
    private final int TILESIZE = Constants.TILE_SIZE.getSize(); // 96x96 Tile Size
    private final int MAXSCREENCOL = 25;
    private final int MAXSCREENROW = 20;
    private final int SCREENWIDTH = TILESIZE * MAXSCREENCOL;
    private final int SCREENHEIGHT = TILESIZE * MAXSCREENROW;
    private final Controller controller;

    // Imagen
    private BufferedImage tileImage;

    public Display(Controller controller) {
        this.controller = controller;
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        try {
            tileImage = ImageIO.read(new File("src\\main\\java\\view\\resources\\tiles\\grass.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

     public int getScreenRow() {
            return MAXSCREENROW;
        }
        public int getScreenCol() {
            return MAXSCREENCOL;
        }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Player player = controller.getPlayer();
        GameMap gameMap = GameMap.getInstance(SCREENWIDTH, SCREENHEIGHT);
        for (int x = 0; x < MAXSCREENCOL; x++) {
            for (int y = 0; y < MAXSCREENROW;y++) {
                // Dibujar el tile en la posición correspondiente
                int tileX = x * Constants.TILE_SIZE.getSize();
                int tileY = y * Constants.TILE_SIZE.getSize();

                
                
                

                // Verificar si el tile es un obstáculo o un tile jugable
                if (gameMap.getMapMatrix()[x][y] == Constants.EMPTY.getSize()) {
                    g.drawImage(tileImage, tileX, tileY, Constants.TILE_SIZE.getSize(), Constants.TILE_SIZE.getSize(),
                            null);
                } else if (gameMap.getMapMatrix()[x][y] == Constants.SPAWN.getSize()) {
                    g.setColor(Color.GRAY); // Color para los bordes
                    g.fillRect(tileX, tileY, Constants.TILE_SIZE.getSize(), Constants.TILE_SIZE.getSize());
                } else if (gameMap.getMapMatrix()[x][y] == Constants.WALL.getSize()) {
                    g.setColor(Color.DARK_GRAY); // Color para los obstáculos
                    g.fillRect(tileX, tileY, Constants.TILE_SIZE.getSize(), Constants.TILE_SIZE.getSize());
                }
            }

        }
        
       
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
