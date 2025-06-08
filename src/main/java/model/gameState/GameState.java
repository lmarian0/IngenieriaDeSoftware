package main.java.model.gameState;
import java.awt.Graphics;
import main.java.controller.Controller;
import main.java.controller.KeyHandler;

public abstract class GameState {

    protected KeyHandler keyHandler;
    protected Controller controller;
    public abstract void update();
    public abstract void draw(Graphics g);

    public GameState(KeyHandler keyHandler, Controller controller) {
        this.keyHandler = keyHandler;
        this.controller = controller;
    }
    
}