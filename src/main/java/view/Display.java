package main.java.view;

import java.awt.*;
import java.awt.event.KeyListener;
import javax.swing.*;
import main.java.controller.Controller;
import main.java.controller.KeyHandler;
import main.java.model.constants.Constants;
import main.java.model.constants.ScreenSettings;
import main.java.model.gameState.*;
import main.java.view.ui.HUD;

public class Display extends JPanel {

    // SCREEN SETTINGS
    private ScreenSettings scSt = ScreenSettings.getInstance();
    private final int FPS = 60;
    private final int TILESIZE = Constants.TILE_SIZE.getSize() * Constants.SCALE.getSize() ; // 96x96 Tile Size
    private final int MAXSCREENCOL = scSt.getScreenCols(); // 20 columns
    private final int MAXSCREENROW = scSt.getScreenRows(); // 10 rows
    private final int SCREENWIDTH = TILESIZE * MAXSCREENCOL;
    private final int SCREENHEIGHT = TILESIZE * MAXSCREENROW;
    private final Controller controller;
    private final HUD hud;

    private static final JButton  startButton = new JButton();
    private static final KeyListener keyHandler = new KeyHandler(); // Instancia del KeyHandler
    

    // Imagen
    
    public Display(Controller controller, KeyListener keyHandler, HUD hud) {
        this.controller = controller;
        this.hud = hud;
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
        this.requestFocusInWindow();

    }

    public int getScreenRow() {
        return MAXSCREENROW;
    }
    public int getScreenCol() {
        return MAXSCREENCOL;
    }

    // Crea un boton de inicio para agregarlo al menu
    private void addButtons() {
        //configuramos el boton de inicio en la posicion segun el porcentaje que corresponde a la imagen del menu
        // (Todo fue calculado mediante mediciones de la imagen del menu y teniendo en cuenta el escalado de la pantalla)
        ScreenSettings settings = ScreenSettings.getInstance();
        startButton.setBounds((int) (settings.getScaledWidth(settings)*0.414), (int) (settings.getScaledHeight(settings)*0.846), (int) (335/settings.transformX()), (int) (110/settings.transformY()));
        this.add(startButton);
        // Hacemos que el boton de inicio sea invisible
        startButton.isOpaque();
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        // Detecta la presion del boton de inicio y te manda a PlayingState
        startButton.addActionListener(e -> {
            if (keyHandler instanceof KeyHandler) {
                try{ controller.setEstadoActual(new PlayingState((KeyHandler) keyHandler, controller)); }
                catch (IllegalThreadStateException ex) {
                    Thread.currentThread().interrupt(); // Interrumpe el hilo actual si ya está en ejecución
                    // Lo hace pq sino actualiza varias veces el estado actual y muestra error
                    // Asi se interrumpe cuando pasa a PlayingState
                }
           
            }
        });

        startButton.setVisible(true);

    }

    // Elimina el boton de inicio del menu
    private void removeButtons() {
        this.remove(startButton);
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) { 
        super.paintComponent(g);
        controller.drawEstadoActual(g);

        // Dibuja el boton solo si el estado actual es MenuState
        // sino lo elimina
        if (controller.getEstadoActual() instanceof MenuState) {
            addButtons();
        }
        else{
            removeButtons();
        }
    }
    
}
