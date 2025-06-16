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
    private boolean animacionCompleta = false;
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
            } else {animacionCompleta = true;}
        } catch (IllegalArgumentException e) {
            // Manejo de excepción si la opacidad se vuelve negativa
            alpha = 0.0f; // Asegura que no sea menor que 0
        }
        
        if (animacionCompleta) {
            // Luego de que la animacion se complete, se cambia al estado del menú
            controller.setEstadoActual(new MenuState(keyHandler, controller));
        }
    }

    @Override

    public void draw(Graphics g) {
        ScreenSettings settings = ScreenSettings.getInstance();
        g.setColor(Color.WHITE);
        g.fill3DRect(0, 0, settings.getScreenWidth() , settings.getScreenHeight() , true);

        Graphics2D g2d = (Graphics2D) g;
        if (alpha < 0.0f) {alpha = 0.0f;} // Asegura que la opacidad no sea menor que 0
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha)); // Aplica transparencia
        g2d.drawImage(loadingImage, 0, 0,  (int) (settings.getScreenWidth() / settings.transformX()), 
            (int) (settings.getScreenHeight() / settings.transformY()), null);
    }
}