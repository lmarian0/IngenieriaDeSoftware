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
    private int MAXSCREENCOL;
    private int MAXSCREENROW;
    private final int SCREENWIDTH;
    private final int SCREENHEIGHT;
    private final Controller controller;
    private final HUD hud;

    // Imagen
    private BufferedImage streetImage, ZemansTileImage, grassTileImage, paliniTileImage;
    private BufferedImage spritePJleft1, spritePJleft2, spritePJright1, spritePJright2;
    private BufferedImage spritePJup1, spritePJup2, spritePJdown1, spritePJdown2;

    public Display(Controller controller, KeyListener keyHandler, HUD hud, int screenWidth, int screenHeight) {
        this.controller = controller;
        this.hud = hud;
        this.MAXSCREENCOL = screenWidth; // Cantidad de columnas de tiles en pantalla
        this.MAXSCREENROW = screenHeight; // Cantidad de filas de tiles en pantalla

        SCREENWIDTH = TILESIZE * MAXSCREENCOL;
        SCREENHEIGHT = TILESIZE * MAXSCREENROW;
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
        this.requestFocusInWindow();


        try {
            streetImage = ImageIO.read(new File("src\\main\\java\\view\\resources\\tiles\\StreetTile.png"));
            ZemansTileImage = ImageIO.read(new File("src\\main\\java\\view\\resources\\tiles\\ZemanskyTile.png"));
            paliniTileImage = ImageIO.read(new File("src\\main\\java\\view\\resources\\tiles\\agarriniLaPaliniTile.png"));
            grassTileImage = ImageIO.read(new File("src\\main\\java\\view\\resources\\tiles\\grass.png"));
        }
        catch (IOException e) {
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
        //int offsetX = player.getPosX() - SCREENWIDTH / 2 + player.getWidth() / 2;
        //int offsetY = player.getPosY() - SCREENHEIGHT / 2 + player.getHeight() / 2;

        // Dibujar enemigos

        
        g.setColor(Color.BLUE);
        for (Enemy enemy : controller.getEnemies()) {
            if(enemy.getIsAlive()) {
                enemy.draw(g, player.getPosX(), player.getPosY());
            } else {
                // Si el enemigo está muerto, no lo dibujamos
                continue;
            }
        }

        

        // Dibujar jugador
        g.drawImage(player.getCurrentSprite(), player.getPosX(), player.getPosY(), player.getWidth()*2, player.getHeight()*2, null);
        
        // DIBUJA EL HUD ARRIBA DE TODO
        hud.draw(g);
    }
}
