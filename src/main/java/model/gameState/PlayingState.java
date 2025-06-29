package main.java.model.gameState;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.java.controller.Controller;
import main.java.controller.KeyHandler;
import main.java.model.Player;
import main.java.model.PowerUpFactory.Power;
import main.java.model.constants.Constants;
import main.java.model.constants.ScreenSettings;
import main.java.model.map.GameMap;
import main.java.view.ui.HUD;

public class PlayingState extends GameState {
    private GameMap gameMap;

    private final int TILESIZE;
    private final int MAXSCREENCOL;
    private final int MAXSCREENROW;
    private final HUD hud;

    	
    private BufferedImage streetImage, ZemansTileImage, grassTileImage;

    public PlayingState(KeyHandler keyHandler, Controller controller) {
        super(keyHandler, controller);
        ScreenSettings scSt = ScreenSettings.getInstance();
        this.TILESIZE = Constants.TILE_SIZE.getSize() * Constants.SCALE.getSize();
        this.MAXSCREENCOL = scSt.getScreenCols();
        this.MAXSCREENROW = scSt.getScreenRows(); 
        int SCREENWIDTH = TILESIZE * MAXSCREENCOL;
        int SCREENHEIGHT = TILESIZE * MAXSCREENROW;
        this.gameMap = GameMap.getInstance(SCREENWIDTH, SCREENHEIGHT);
        this.hud = new HUD(controller.getPlayer()); // Initialize the final field 'hud'
        this.controller.startThreads(); // Start the enemy spawner thread
        try {
            streetImage = ImageIO.read(new File("src\\main\\java\\view\\resources\\tiles\\StreetTile.png"));
            ZemansTileImage = ImageIO.read(new File("src\\main\\java\\view\\resources\\tiles\\ZemanskyTile.png"));
            grassTileImage = ImageIO.read(new File("src\\main\\java\\view\\resources\\tiles\\grass.png"));
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

        if(controller.getPlayer().getHp() == 0) {
            controller.setEstadoActual(new GameOverState(keyHandler, controller)); // Transition to PlayingState when space is pressed
        }
        controller.updateEnemies();
        controller.updateAlly();
        controller.handlePlayerInput();
        controller.updatePowerUp();
        controller.getPlayer().updateAttackState();
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
            controller.getAlly().draw(g, (TILESIZE*MAXSCREENCOL-controller.getAlly().getWidth())/3, 0);
            controller.getAllyTextBar().draw(g,(TILESIZE*MAXSCREENCOL-controller.getAlly().getWidth())/3+controller.getAlly().getWidth(), 0, TILESIZE);
        }else if(Player.getInstance().getXp() >= 100){
            controller.CallAdvice(g, MAXSCREENCOL, TILESIZE);
        }

        //Dibuja powerUps
        if(controller.getProduction()){
            for(Power powerUp: controller.getPowerUps()){
                powerUp.draw(g);
            }
        }
        
        // Dibuja el HUD
        hud.draw(g);

        
    }
}