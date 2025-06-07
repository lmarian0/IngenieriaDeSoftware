package main.java.controller;

import main.java.model.Factory.EnemySpawner;
import main.java.model.Factory.GoblinFactory;
import main.java.model.constants.Direction;
import main.java.view.Display;
import main.java.model.Enemy;
import main.java.model.Player;

import java.util.List;

public class Controller {
    private Player enzito;
    private KeyHandler keyHandler;
    private EnemySpawner spawner;
    private final Camera camera = new Camera();
    private boolean flagDead = false;
    
    public Controller (Player enzito, KeyHandler keyHandler) {
        this.enzito = enzito;
        this.keyHandler = keyHandler;
        spawner = new EnemySpawner();
        Thread venThread = new Thread(spawner);
        venThread.start();
    }

    public void update() {
        handlePlayerInput();
        updateEnemies();
        camera.update(enzito);

        if (keyHandler.space && enzito.isAlive()) {
            for (Enemy enemy : spawner.getGeneratedEnemies()) {
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
            System.out.println(enzito.getPosX() + " " + enzito.getPosY());
            flagDead = true; // para que solo se imprima una vez
        }
    }

    public void updateEnemies() {
        spawner.setPlayerPos(enzito.getPosX(), enzito.getPosY());
        for(Enemy enemy: getEnemies()) {
            if(!enemy.getIsAlive()){
                removeEnemy(enemy); // Elimina el enemigo de la lista si está muerto
                enemy = null;
                continue;
            }
            enemy.addObserver(enzito);
            enemy.chase(enzito.getPosX(), enzito.getPosY(), getEnemies());
            enemy.attack(enzito);
        }
    }

    public Player getPlayer() {
        return enzito;
    }

    public List<Enemy> getEnemies() {
        return spawner.getGeneratedEnemies();
    }

    public void removeEnemy(Enemy enemy) {
        spawner.getGeneratedEnemies().remove(enemy);
    }

    public Camera getCamera() {
        return camera;
    }

}

