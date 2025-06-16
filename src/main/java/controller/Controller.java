package main.java.controller;

import java.awt.Graphics;
import java.util.List;
import java.util.Random;
import main.java.model.Enemy;
import main.java.model.Factory.EnemySpawner;
import main.java.model.Player;
import main.java.model.PowerUpFactory.Power;
import main.java.model.PowerUpFactory.PowerUpFactory;
import main.java.model.character.Ally;
import main.java.model.constants.Direction;
import main.java.model.gameState.GameState;
import main.java.view.AllyTextBar.AllyTextBar;


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
    private Thread venThread;
    private PowerUpFactory spawnerPowerUp;
    private Thread venThreadLaVenganza;

    public Controller (Player enzito, KeyHandler keyHandler) {
        this.enzito = enzito;
        this.aliado = null;
        this.keyHandler = keyHandler;
        this.spawner = new EnemySpawner();
        this.spawnerPowerUp = new PowerUpFactory(aliado);
        this.venThread = new Thread(spawner);
        this.venThreadLaVenganza = new Thread(spawnerPowerUp);
    }

    public void startThreads(){
        this.venThread.start();
        this.venThreadLaVenganza.start();
    } 

    public void update() {
        if (estadoActual != null) {
            estadoActual.update();
        }
    }

    public void handlePlayerInput() {
        if (enzito.isAlive()) { // Si está vivo puede moverse o atacar

            // Movimiento
            if (keyHandler.up) enzito.move(Direction.UP);
            if (keyHandler.down) enzito.move(Direction.DOWN);
            if (keyHandler.left) enzito.move(Direction.LEFT);
            if (keyHandler.right) enzito.move(Direction.RIGHT);

            // Ataque (solo cuando se presiona la tecla una vez)
            if (keyHandler.space) {
                handleAttack();
            }

        } else if (!flagDead) {
            System.out.println("¡El jugador ha muerto! No se puede mover.");
            System.out.println("Murió en las coordenadas: ("+enzito.getPosX() + ";" + enzito.getPosY()+")");
            flagDead = true; // para que solo se imprima una vez
            spawnerPowerUp.stop();
            spawner.stop();
        }
    }

    private void handleAttack() {
        keyHandler.space = false; // solo una vez por tecla
        enzito.setAttacking(true); // activa flag de ataque
        enzito.updateSprite();     // actualiza el sprite inmediatamente
        for (Enemy enemy : getEnemies()) {
            if(enemy.getIsAlive()){
                enzito.attack(enemy);
            }
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
        }
    }

    public void updatePowerUp(){
        if(spawnerPowerUp.getProduction()){
            for(Power powerUp: getPowerUps()){
                if(powerUp.active(enzito)){
                    powerUp.take(enzito);
                    spawnerPowerUp.delGeneratedPowerUps(powerUp);
                    powerUp=null;
                }
            }
        }else{
            enzito.restoreDmg();
            enzito.restoreSpeed();
        }
    }

    private Ally createAlly() {
        Ally ally;
        numero = rand.nextInt(4); // Genera un número aleatorio entre 0 y 2
        switch (numero) {
            case 0:
                ally = new Ally("Sergio", "Soy el Figue, comete un chori saludable dale","src\\main\\java\\view\\resources\\Aliades\\Sergio\\MilitarSergio.png");
                break;
            case 1:
                ally = new Ally("Nikito", "Soy Nikito nitoka, tomate un fernet", "src\\main\\java\\view\\resources\\Aliades\\Nikito\\NikitoSprite.png");
                break;
            case 2:
                ally = new Ally("Danilo", "Soy Danilo, aguante Argentina locooo","src\\main\\java\\view\\resources\\Aliades\\Danilo\\DaniloSprite.png");
                break;
            default:
                ally = new Ally("JuanMa", "Soy Juanma, sebate un mate king","src\\main\\java\\view\\resources\\Aliades\\JuanMa\\JuanMaSprite.png");
                break;
        }
        return ally;
    }

    public void updateAlly(){
        if(aliado==null && enzito.getXp()>=100 && keyHandler.k){
            aliado = createAlly();
            textAliado = new AllyTextBar(aliado);
            enzito.subXp(100); // Resta 300 de experiencia al jugador
            this.spawnerPowerUp.setAlly(aliado);
            this.spawnerPowerUp.setProduction(true);
        }
        if(aliado != null) {
            if(aliado.getClkDuration() < 625) {
                aliado.addClkDuracion();
            }else {
                this.spawnerPowerUp.setProduction(false);
                aliado = null; // Elimina al aliado si ha pasado el tiempo
                textAliado = null; // Elimina la barra de texto del aliado
                // Detiene el hilo de generación de PowerUps
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

    public List<Power> getPowerUps(){
        return spawnerPowerUp.getGeneratedPowerUps();
    }

    public Ally getAlly(){
        if(aliado != null){
            return aliado;
        }
        return null;
    }

    public boolean getProduction(){
        return spawnerPowerUp.getProduction();
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

    public GameState getEstadoActual() {
        return estadoActual;
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


