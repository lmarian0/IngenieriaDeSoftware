package main.java.model.gameState;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.java.controller.Controller;
import main.java.controller.KeyHandler;
import main.java.model.Player;
import main.java.model.constants.Constants;
import main.java.model.map.GameMap;
import main.java.view.Display;
import main.java.view.ui.HUD;
import main.java.view.ui.HUD;

public class PlayingState extends GameState {
    private GameMap gameMap;
    private BufferedImage streetImage, ZemansTileImage, grassTileImage;
    private final int TILESIZE;
    private final int MAXSCREENCOL;
    private final int MAXSCREENROW;

    	
    public PlayingState(KeyHandler keyHandler, Controller controller) {
        super(keyHandler, controller);
        this.TILESIZE = Constants.TILE_SIZE.getSize() * Constants.SCALE.getSize();
        this.MAXSCREENCOL = 20;
        this.MAXSCREENROW = 10;
        int SCREENWIDTH = TILESIZE * MAXSCREENCOL;
        int SCREENHEIGHT = TILESIZE * MAXSCREENROW;
        this.gameMap = GameMap.getInstance(SCREENWIDTH, SCREENHEIGHT);
        try {
            streetImage = ImageIO.read(new File("src/main/java/view/resources/tiles/StreetTile.png"));
            ZemansTileImage = ImageIO.read(new File("src/main/java/view/resources/tiles/ZemanskyTile.png"));
            grassTileImage = ImageIO.read(new File("src/main/java/view/resources/tiles/grass.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        if(controller.getPlayer().getHp() == 0) {
            controller.setEstadoActual(new GameOverState(keyHandler, controller)); // Transition to PlayingState when space is pressed

        }   
    }

    public int getScreenRow() {
        return MAXSCREENROW;
    }
    public int getScreenCol() {
        return MAXSCREENCOL;
    }

    @Override
    public void draw(Graphics g) {
        for (int x = 0; x < MAXSCREENCOL; x++) {
            for (int y = 0; y < MAXSCREENROW; y++) {
                int tileX = x * TILESIZE;
                int tileY = y * TILESIZE;
                if (gameMap.getMapMatrix()[x][y] == Constants.EMPTY.getSize()) {
                    g.drawImage(streetImage, tileX, tileY, TILESIZE, TILESIZE, null);
                } else if (gameMap.getMapMatrix()[x][y] == Constants.SPAWN.getSize()) {
                    g.drawImage(grassTileImage, tileX, tileY, TILESIZE, TILESIZE, null);
                } else if (gameMap.getMapMatrix()[x][y] == Constants.WALL.getSize()) {
                    g.drawImage(ZemansTileImage, tileX, tileY, TILESIZE, TILESIZE, null);
                }
            }
        }
        // Dibuja enemigos
        g.setColor(java.awt.Color.BLUE);
        main.java.model.Player player = controller.getPlayer();
        for (main.java.model.Enemy enemy : controller.getEnemies()) {
            if (enemy.getIsAlive()) {
                enemy.draw(g, player.getPosX(), player.getPosY());
            }
        }
        // Dibuja jugador
        g.setColor(java.awt.Color.RED);
        g.fillRect(player.getPosX(), player.getPosY(), player.getWidth() * 2, player.getHeight() * 2);
        // Dibuja el HUD

        controller.updateEnemies();
    }
}