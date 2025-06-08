package main.java.model.gameState;
import java.awt.Graphics;

import main.java.controller.Controller;
import main.java.controller.KeyHandler;

public class LevelUpState extends GameState {

    public LevelUpState(KeyHandler keyHandler , Controller controller) {
        super(keyHandler , controller);
        
    }

    @Override
    public void update() {
    }

    @Override

    public void draw(Graphics g) {
        g.drawString("LevelUP", 100, 100); // Draw "Menu" message at position (100, 100)
    }
}