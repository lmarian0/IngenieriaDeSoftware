package main.java.model.gameState;
import java.awt.*;

import main.java.controller.Controller;
import main.java.controller.KeyHandler;

public class GameOverState extends GameState {

    public GameOverState(KeyHandler keyHandler , Controller controller) {
        super(keyHandler , controller);
        
    }

    @Override
    public void update() {
    }

    @Override

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(100, 100, 100,200);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString("Moriste :v", 600, 300);
    }
}
