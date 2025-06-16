package main.java.model.gameState;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import main.java.controller.Controller;
import main.java.controller.KeyHandler;
import main.java.model.constants.ScreenSettings;

public class GameOverState extends GameState {
    private BufferedImage SIUfree;
    private ScreenSettings settings = ScreenSettings.getInstance();
    private int width = (int) (settings.getScreenWidth() / settings.transformX());
    private int height = (int) (settings.getScreenHeight() / settings.transformY());
    private int alternateCounter = 0;
    private boolean showMessage = true;

    

    public GameOverState(KeyHandler keyHandler , Controller controller) {
        super(keyHandler , controller);
        try {
            SIUfree = ImageIO.read(new File("src\\main\\java\\view\\resources\\Sprites\\SIU.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void update() {

        // Por cada actualización, se incrementa el contador alternante que nos permite alternar el mensaje de "Press ESC to quit game..."
        // entre visible y no visible.
        alternateCounter++;
        if (alternateCounter > 40) { // Ajustá según el tiempo que querés
            showMessage = !showMessage;
            alternateCounter = 0;
        }

    }

    @Override

    public void draw(Graphics g) {
        //Se dibuja el game over centrado y al 30% de la altura de la pantalla
        //Se le asigna su color, tamaño y fuente
        g.setColor(Color.BLACK);
        g.setColor(Color.WHITE);
        g.setFont(new Font( "Unispace", Font.BOLD, 60));
        drawStringCentradoX(g, "Game Over... ¡MANCO!", width / 2, (int) (height*0.3));

        // Se dibuja la imagen del SIU
        // Se ajusta el tamaño de la imagen al 50% del ancho y alto de la pantalla
        int imageWidth = (int) (SIUfree.getWidth()*settings.transformX());
        int imageHeight = (int) (SIUfree.getHeight()*settings.transformY());
        g.drawImage(SIUfree, (width/2)-(imageWidth/2), height/2, imageWidth, imageHeight, null);

        // Se dibuja el mensaje de "Press ESC to quit game..."
        quitGameMsg(g);
    }

    /**
     * Dibuja un mensaje alternante de "Press ESC to quit game...".
     *
     * @param g El objeto Graphics donde se dibuja el mensaje.
     */
    private void quitGameMsg(Graphics g) {
        // Mensaje alternante de "Press ESC to quit game..." 
        g.setColor(Color.WHITE);
        drawStringCentradoX(g, "Press ESC to quit game...", width / 2, (int) (height*0.9));
       if (showMessage) {
        g.setColor(Color.BLACK);
        drawStringCentradoX(g, "Press ESC to quit game...", width / 2, (int) (height*0.9));
        }
    }

    /**
     * Dibuja un texto centrado en el eje X.
     *
     * @param g El objeto Graphics donde se dibuja el texto.
     * @param text El texto a dibujar.
     * @param centerX La coordenada X donde se centra el texto.
     * @param y La coordenada Y donde se dibuja el texto.
     */
    public void drawStringCentradoX(Graphics g, String text, int centerX, int y) {
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int x = centerX - metrics.stringWidth(text) / 2;
        g.drawString(text, x, y);
    }

}
