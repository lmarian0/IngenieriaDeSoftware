package main.java.controller;

import main.java.model.Factory.EnemySpawner;
import main.java.model.Factory.GoblinFactory;
import main.java.model.constants.Direction;
import main.java.model.gameState.GameState;
import main.java.view.Display;
import main.java.view.AllyTextBar.AllyTextBar;
import main.java.model.Enemy;
import main.java.model.Player;
import main.java.model.character.Ally;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;
import main.java.model.character.Ally;

public class Controller {
    private Player enzito;
    private Ally aliado;
    private KeyHandler keyHandler;
    private EnemySpawner spawner;
    private GameState estadoActual;
    private final Camera camera = new Camera();
    private boolean flagDead = false;
    private Random rand = new Random();
    private int numero;
    private AllyTextBar textAliado;

    public Controller (Player enzito, KeyHandler keyHandler) {
        this.enzito = enzito;
        this.aliado = null;
        this.keyHandler = keyHandler;
        spawner = new EnemySpawner();
        Thread venThread = new Thread(spawner);
        venThread.start();
    }

    public void update() {
        if (estadoActual != null) {
            estadoActual.update();
        }
    }

    public void handlePlayerInput() {
        if (enzito.isAlive()) { // Para que el PJ no se mueva luego de muerto
            if (keyHandler.up)      enzito.move(Direction.UP);
            if (keyHandler.down)    enzito.move(Direction.DOWN);
            if (keyHandler.left)    enzito.move(Direction.LEFT);
            if (keyHandler.right)   enzito.move(Direction.RIGHT);
            
        } else if (!flagDead) {
            System.out.println("¡El jugador ha muerto! No se puede mover.");
            System.out.println(enzito.getPosX() + " " + enzito.getPosY());
            flagDead = true; // para que solo se imprima una vez
            spawner.stop();
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
            enemy.addObserver(enzito);
            enemy.attack(enzito);
            // ATAQUE CON SPACE
            if(keyHandler.space) enzito.attack(enemy);
        }
    }

    private Ally createAlly() {
        Ally ally;
        numero = rand.nextInt(4); // Genera un número aleatorio entre 0 y 2
        switch (numero) {
            case 0:
                ally = new Ally("Sergio", "¡Hola, soy Sergio Chad y soy re capo!");
                break;
            case 1:
                ally = new Ally("Nikito", "¡Hola, soy Nikito, tomate un fernet!");
                break;
            case 2:
                ally = new Ally("Danilo", "¡Hola, soy Danilo, mirate tu cuenta de Naranja X ;)!");
                break;
            default:
                ally = new Ally("JuanMa", "¡Hola, soy JuanMa mansa barba tengo!");
                break;
        }
        return ally;
    }

    public void updateAlly(){
        if(aliado==null && enzito.getXp()>=100 && keyHandler.k){
            aliado = createAlly();
            textAliado = new AllyTextBar(aliado);
            System.out.println("¡Hola soy " + aliado.getName() + " y soy tu aliado!");
            System.out.println(aliado.speak());
            enzito.subXp(100); // Resta 300 de experiencia al jugador
        }
        if(aliado != null) {
            if(aliado.getClkDuration() < 625) {
                aliado.addClkDuracion();
            }else {
                aliado = null; // Elimina al aliado si ha pasado el tiempo
                textAliado = null; // Elimina la barra de texto del aliado
                System.out.println("El aliado ha desaparecido.");
            }
        }
    }

    public Player getPlayer() {
        return enzito;
    }

    public List<Enemy> getEnemies() {
        return spawner.getGeneratedEnemies();
    }

    public Ally getAlly(){
        if(aliado != null){
            return aliado;
        }
        return null;
    }

    public AllyTextBar getAllyTextBar() {
        if(textAliado != null){
            return textAliado;
        }
        return null;
    }

    public void removeEnemy(Enemy enemy) {
        spawner.getGeneratedEnemies().remove(enemy);
    }

    public Camera getCamera() {
        return camera;
    }

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


