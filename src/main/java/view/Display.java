package main.java.view;

import main.java.controller.Controller;
import main.java.model.map.GameMap;
import main.java.model.Enemy;
import main.java.model.Player;
import main.java.model.constants.Constants;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.*;
import java.awt.*;

import main.java.view.ui.HUD;

public class Display extends JPanel {

    // SCREEN SETTINGS
    private final int FPS = 60;
    private final int TILESIZE = Constants.TILE_SIZE.getSize() * Constants.SCALE.getSize() ; // 96x96 Tile Size
    private final int MAXSCREENCOL = 24;
    private final int MAXSCREENROW = 12;
    private final int SCREENWIDTH = TILESIZE * MAXSCREENCOL;
    private final int SCREENHEIGHT = TILESIZE * MAXSCREENROW;
    private final Controller controller;
    private final HUD hud;

    // Imagen
    private BufferedImage streetImage, ZemansTileImage, grassTileImage;
    private BufferedImage spritePJleft1, spritePJleft2, spritePJright1, spritePJright2;
    private BufferedImage spritePJup1, spritePJup2, spritePJdown1, spritePJdown2;

    public Display(Controller controller, KeyListener keyHandler, HUD hud) {
        this.controller = controller;
        this.hud = hud;
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
        this.requestFocusInWindow();


        try {
            streetImage = ImageIO.read(new File("src\\main\\java\\view\\resources\\tiles\\StreetTile.png"));
            ZemansTileImage = ImageIO.read(new File("src\\main\\java\\view\\resources\\tiles\\ZemanskyTile.png"));
            grassTileImage = ImageIO.read(new File("src\\main\\java\\view\\resources\\tiles\\grass.png"));
            //Sprites for Player
            spritePJleft1 = ImageIO.read(new File("src\\main\\java\\view\\resources\\player\\p1_left_1.png"));
            spritePJleft2 = ImageIO.read(new File("src\\main\\java\\view\\resources\\player\\p1_left_2.png"));
            spritePJright1 = ImageIO.read(new File("src\\main\\java\\view\\resources\\player\\p1_right_1.png"));
            spritePJright2 = ImageIO.read(new File("src\\main\\java\\view\\resources\\player\\p1_right_2.png"));
            spritePJup1 = ImageIO.read(new File("src\\main\\java\\view\\resources\\player\\p1_up_1.png"));
            spritePJup2 = ImageIO.read(new File("src\\main\\java\\view\\resources\\player\\p1_up_2.png"));
            spritePJdown1 = ImageIO.read(new File("src\\main\\java\\view\\resources\\player\\p1_down_1.png"));
            spritePJdown2 = ImageIO.read(new File("src\\main\\java\\view\\resources\\player\\p1_down_2.png"));
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


        GameMap gameMap = GameMap.getInstance(SCREENWIDTH, SCREENHEIGHT);
        for (int x = 0; x < MAXSCREENCOL; x++) {
            for (int y = 0; y < MAXSCREENROW; y++) {
                // Dibujar el tile en la posición correspondiente (afecta la pos segun los px)
                int tileX = x * TILESIZE;
                int tileY = y * TILESIZE;

                // Verificar si el tile es un obstáculo o un tile jugable
                if (gameMap.getMapMatrix()[x][y] == Constants.EMPTY.getSize()) {
                    g.drawImage(streetImage, tileX, tileY, TILESIZE, TILESIZE, null);
                } else if (gameMap.getMapMatrix()[x][y] == Constants.SPAWN.getSize()) {
                    g.drawImage(grassTileImage, tileX, tileY, TILESIZE, TILESIZE, null);
                } else if (gameMap.getMapMatrix()[x][y] == Constants.WALL.getSize()) {
                    g.drawImage(ZemansTileImage, tileX, tileY, TILESIZE, TILESIZE, null);
                }
            }

        }

        Player player = controller.getPlayer();
        // Centramos el jugador en el medio de la pantalla
        int offsetX = player.getPosX() - SCREENWIDTH / 2 + player.getWidth() / 2;
        int offsetY = player.getPosY() - SCREENHEIGHT / 2 + player.getHeight() / 2;

        // Dibujar enemigos
        g.setColor(Color.BLUE);
        for (Enemy enemy : controller.getEnemies()) {
            g.fillRect(enemy.getPosX() - offsetX, enemy.getPosY() - offsetY, enemy.getWidth() * 2, enemy.getHeight() * 2);
        }

        // Dibujar jugador
        g.setColor(Color.RED);
        g.fillRect(player.getPosX() - offsetX, player.getPosY() - offsetY, player.getWidth() * 2, player.getHeight() * 2);

        // DIBUJA EL HUD ARRIBA DE TODO
        hud.draw(g);
    }
}
