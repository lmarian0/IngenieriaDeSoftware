package main.java.model.gameState;
import java.awt.Graphics;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import main.java.controller.Controller;
import main.java.controller.KeyHandler;
import main.java.model.constants.ScreenSettings;

public class MenuState extends GameState {


    private BufferedImage menuImage;
    private ScreenSettings settings = ScreenSettings.getInstance();
    private int width = (int) (settings.getScreenWidth() / settings.transformX());
    private int height = (int) (settings.getScreenHeight() / settings.transformY());

    public MenuState(KeyHandler keyHandler , Controller controller) {
        super(keyHandler , controller);
        try {
            menuImage = ImageIO.read(new File("src\\main\\java\\view\\resources\\Sprites\\MenuBackground.jpeg"));
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update() {
        if (keyHandler.space) {
            controller.setEstadoActual(new PlayingState(keyHandler, controller)); // Transition to PlayingState when space is pressed
            keyHandler.space = false; // Reset the space key state to prevent multiple transitions    
        }
    }

    @Override

    public void draw(Graphics g) {
        g.drawImage(menuImage, 0, 0,  width, height, null);
    }

    

}
