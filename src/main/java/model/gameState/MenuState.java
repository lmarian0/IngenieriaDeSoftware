package main.java.model.gameState;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;

import java.awt.Graphics;
import main.java.controller.Controller;
import main.java.controller.KeyHandler;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import main.java.model.constants.ScreenSettings;
import main.java.view.Display;

public class MenuState extends GameState {

    private JButton startButton;

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
        addButtons();

    }

    @Override
    public void update() {
        if (keyHandler.space) {
            removeButtons(); // Eliminar botón antes de cambiar de estado
            controller.setEstadoActual(new PlayingState(keyHandler, controller)); // Transition to PlayingState when space is pressed
            keyHandler.space = false; // Reset the space key state to prevent multiple transitions    
        }
    }

    @Override

    public void draw(Graphics g) {
        g.drawImage(menuImage, 0, 0,  width, height, null);
    }
   

    private void addButtons() {
        startButton = new JButton("Start Game");

        startButton.setBounds((int) (width * 0.41), (int) (height * 0.84), 
                            (int) (485 * settings.transformX()), (int) (155 * settings.transformY()));
        startButton.setOpaque(true);
        startButton.setContentAreaFilled(true);
        startButton.setBorderPainted(true);

        // Evento para cambiar a `PlayingState`
        startButton.addActionListener(e -> {    
            removeButtons(); // Eliminar botón antes de cambiar de estado
            controller.setEstadoActual(new PlayingState(keyHandler, controller));
        });

        if (controller.getFrame() != null) {
            controller.getFrame().add(startButton);
            controller.getFrame().revalidate();
            controller.getFrame().repaint();
        } else {
            System.out.println("Error: Controller.getFrame() es null. Verifica que Controller tenga un JFrame inicializado.");
        }

    }

    private void removeButtons() {
    if (controller.getFrame() != null) {
        controller.getFrame().remove(startButton);
        controller.getFrame().revalidate();
        controller.getFrame().repaint();
    }
    }
}
