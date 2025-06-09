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

    private final int TILESIZE;
    private final int MAXSCREENCOL;
    private final int MAXSCREENROW;
    private final HUD hud;
    	
    private BufferedImage streetImage, ZemansTileImage, grassTileImage;
    private BufferedImage spritePJleft1, spritePJleft2, spritePJright1, spritePJright2;
    private BufferedImage spritePJup1, spritePJup2, spritePJdown1, spritePJdown2;

    public PlayingState(KeyHandler keyHandler, Controller controller) {
        super(keyHandler, controller);
        this.TILESIZE = Constants.TILE_SIZE.getSize() * Constants.SCALE.getSize();
        this.MAXSCREENCOL = 20;
        this.MAXSCREENROW = 10;
        int SCREENWIDTH = TILESIZE * MAXSCREENCOL;
        int SCREENHEIGHT = TILESIZE * MAXSCREENROW;
        this.gameMap = GameMap.getInstance(SCREENWIDTH, SCREENHEIGHT);
        this.hud = new HUD(controller.getPlayer()); // Initialize the final field 'hud'
        
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

    @Override
    public void update() {
        controller.handlePlayerInput();

        // ATAQUE CON SPACE
        Player enzito = controller.getPlayer();
        if (keyHandler.space && enzito.isAlive()) {
            for (main.java.model.Enemy enemy : controller.getEnemies()) {
                int dx = Math.abs(enemy.getPosX() - enzito.getPosX());
                int dy = Math.abs(enemy.getPosY() - enzito.getPosY());

                if (dx < 20 && dy < 20 && enemy.getIsAlive()) {
                    enemy.takeDamage(5);  // daño de ataque del Player
                    System.out.println("¡Ataque exitoso!");
                }
            }
        }

        if(controller.getPlayer().getHp() == 0) {
            controller.setEstadoActual(new GameOverState(keyHandler, controller)); // Transition to PlayingState when space is pressed
        }
        controller.updateEnemies();
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
        g.drawImage(player.getCurrentSprite(), player.getPosX(), player.getPosY(), player.getWidth()*2, player.getHeight()*2, null);

        //Dibuja aliados
        
        if(controller.getAlly() != null){
            controller.getAlly().draw(g, (TILESIZE*MAXSCREENCOL-controller.getAlly().getWidth())/2, 0);
        }
        
        // Dibuja el HUD
        hud.draw(g);

        
    }
}