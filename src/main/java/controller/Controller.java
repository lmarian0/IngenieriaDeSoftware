package main.java.controller;

import main.java.model.constants.Direction;
import main.java.model.Enemy;
import main.java.model.Player;

import java.util.List;

public class Controller {
    private Player enzito;
    private KeyHandler keyHandler;
    private List<Enemy> enemies;
    private final Camera camera = new Camera();

    public Controller (Player enzito, List<Enemy> enemies, KeyHandler keyHandler) {
        this.enzito = enzito;
        this.keyHandler = keyHandler;
        this.enemies = enemies;
    }

    public void update() {
        handlePlayerInput();
        updateEnemies();
        camera.update(enzito);
    }

    private void handlePlayerInput() {
        if (keyHandler.up) enzito.move(Direction.UP);
        if (keyHandler.down) enzito.move(Direction.DOWN);
        if (keyHandler.left) enzito.move(Direction.LEFT);
        if (keyHandler.right) enzito.move(Direction.RIGHT);
    }

    public void updateEnemies() {
        for(Enemy enemy: enemies) {
            enemy.chase(enzito.getPosX(), enzito.getPosY());
        }
    }

    public Player getPlayer() {
        return enzito;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Camera getCamera() {
        return camera;
    }

}

