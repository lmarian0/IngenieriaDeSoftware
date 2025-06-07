package main.java.controller;

import main.java.model.Factory.EnemySpawner;
import main.java.model.Factory.GoblinFactory;
import main.java.model.constants.Direction;
import main.java.model.gameState.GameState;
import main.java.view.Display;
import main.java.model.Enemy;
import main.java.model.Player;
import main.java.model.gameState.GameState;

import java.awt.Graphics;
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
        printEstadoActual();
        handlePlayerInput();
        camera.update(enzito);

        
        if (estadoActual!=null){
            estadoActual.update(); // Actualiza el estado actual del juego
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
        for(Enemy enemy: getEnemies()) {
            if(!enemy.getIsAlive()){
                enemy = null;
                removeEnemy(enemy); // Elimina el enemigo de la lista si está muerto
                continue;
            }
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

    //estados

    private GameState estadoActual;

    public void setEstadoActual(GameState state) {
        this.estadoActual = state;
    }

    public void updateEstadoActual() {
        if (estadoActual != null) {
            estadoActual.update();
        }
    }

    public void drawEstadoActual(Graphics g) {
        if (estadoActual != null) {
            estadoActual.draw(g);
        }
    }
    public void printEstadoActual() {
    if (estadoActual == null) {
        System.out.println("El estado actual es: null");
        } else {
        System.out.println("El estado actual es: " + estadoActual.getClass().getSimpleName());
        }
    }
}

