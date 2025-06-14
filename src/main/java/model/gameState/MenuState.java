package main.java.model.gameState;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import main.java.controller.Controller;
import main.java.controller.KeyHandler;

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
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString("Menu Principal", 500, 300);
    }
}