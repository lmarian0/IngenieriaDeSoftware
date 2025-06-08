package main.java.model.gameState;
import java.awt.Color;
import java.awt.Graphics;
import main.java.controller.Controller;
import main.java.controller.KeyHandler;
import java.awt.Font;

public class MenuState extends GameState {

    public MenuState(KeyHandler keyHandler , Controller controller) {
        super(keyHandler , controller);

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
        g.setColor(Color.BLACK);
        g.fillRect(100, 100, 100,200);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString("Menu Principal", 100, 100);
    }
}