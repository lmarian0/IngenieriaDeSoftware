package main.java.controller;

import main.java.model.constants.Direction;
import main.java.view.Display;
import main.java.model.Enemy;
import main.java.model.Player;

import java.util.List;

public class Controller {
    private Player enzito;
    private KeyHandler keyHandler;
    private List<Enemy> enemies;
    private final Camera camera = new Camera();
    private boolean flagDead = false;
    
    public Controller (Player enzito, List<Enemy> enemies, KeyHandler keyHandler) {
        this.enzito = enzito;
        this.keyHandler = keyHandler;
        this.enemies = enemies;
    }

    public void update() {
        handlePlayerInput();
        updateEnemies();
        camera.update(enzito);

        if (keyHandler.space && enzito.isAlive()) {
            for (Enemy enemy : enemies) {
                int dx = Math.abs(enemy.getPosX() - enzito.getPosX());
                int dy = Math.abs(enemy.getPosY() - enzito.getPosY());

                if (dx < 20 && dy < 20 && enemy.getIsAlive()) {
                    enemy.takeDamage(5);  // daño de ataque del Player
                    System.out.println("¡Ataque exitoso!");
                }
            }
        }

    }

    private void handlePlayerInput() {
        if (enzito.isAlive()) { // Para que el PJ no se mueva luego de muerto
            if (keyHandler.up)      enzito.move(Direction.UP);
            if (keyHandler.down)    enzito.move(Direction.DOWN);
            if (keyHandler.left)    enzito.move(Direction.LEFT);
            if (keyHandler.right)   enzito.move(Direction.RIGHT);

        } else if (!flagDead) {
            System.out.println("¡El jugador ha muerto! No se puede mover.");
            flagDead = true; // para que solo se imprima una vez
        }
    }

    public void updateEnemies() {
        for(Enemy enemy: enemies) {
            if(enemy.getIsAlive() == false){
                enemy = null;
                enemies.remove(enemy); // Elimina el enemigo de la lista si está muerto
                continue;
            }
            enemy.chase(enzito.getPosX(), enzito.getPosY(), getEnemies());

            // Verifica si está lo suficientemente cerca para atacar
            int dx = Math.abs(enemy.getPosX() - enzito.getPosX());
            int dy = Math.abs(enemy.getPosY() - enzito.getPosY());

            if (dx < 20 && dy < 20) {
                enemy.attack(enzito);  // Ahora el player recibe daño
            }
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

