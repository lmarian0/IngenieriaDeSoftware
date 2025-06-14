package main.java.model.gameState;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import main.java.controller.Controller;
import main.java.controller.KeyHandler;
import main.java.model.constants.ScreenSettings;

public class LoadingState extends GameState {

    private BufferedImage loadingImage;
    private boolean isLoadingComplete = false;
    private float alpha = 1.0f; // Opacity level, starts fully opaque

    public LoadingState(KeyHandler keyHandler , Controller controller) {
        super(keyHandler , controller);
         try {
            loadingImage = ImageIO.read(new File("src\\main\\java\\view\\resources\\Sprites\\Java el mate.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
       

    }

    @Override
    public void update() {

        try {
            if (alpha > 0) {
                alpha -= 0.01f; // Reduce la opacidad gradualmente
            } else {
                isLoadingComplete = true;
            }

            if (isLoadingComplete) {
                // Transition to the next state after loading is complete
                controller.setEstadoActual(new MenuState(keyHandler, controller));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override

    public void draw(Graphics g) {
        ScreenSettings settings = ScreenSettings.getInstance();
        g.setColor(Color.WHITE);
        g.fill3DRect(0, 0, settings.getScreenWidth() , settings.getScreenHeight() , true);

        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha)); // Aplica transparencia
        g2d.drawImage(loadingImage, 0, 0,  (int) (settings.getScreenWidth() / settings.transformX()), 
            (int) (settings.getScreenHeight() / settings.transformY()), null);
    }
}