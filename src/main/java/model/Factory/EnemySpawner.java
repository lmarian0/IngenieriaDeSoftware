package main.java.model.Factory;

import main.java.model.Enemy;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.util.Random;

public class EnemySpawner implements Runnable {

    /*
     * Clase creada, encargada de spawnear a los enemigos segun el Factory
     * correspondiente.
     * Para crear otro generador de Enemigos, tienen que añadirlo como atributo a
     * esta clase.
     * Ej: TrapitoFactory, el cual genere trapitos, y que tenga un metodo como el
     * siguiente:
     * generatedEnemies.add(trapitoFactory.createEnemy(10,20));
     * Ahora, en el caso de la posicion de generacion, para mi hay que implementar
     * un Singleton en Player,
     * el unico Singleton que deberiamos implementar en si, porque acá necesitamos
     * si o si el dato de la
     * posX y posY del Player, y como no tenemos forma de meter al Player a esta
     * clase sin acoplar todo,
     * conviene invocar a un metodo tipo
     * generatedEnemies.add(gobSpawner.createEnemy(Player.getInstance().getPosX +
     * 30, Player.getInstance().getPosY + 30));
     * para que se creen a una distancia específica del Player.
     */

    private int playerPosX, playerPosY;
    private GoblinFactory goblinFactory;
    private NaranjitaFactory naranjitaFactory;
    private TankFactory tankFactory;
    private boolean running;
    private List<Enemy> generatedEnemies;

    public EnemySpawner() {
        this.goblinFactory = new GoblinFactory();
        this.naranjitaFactory = new NaranjitaFactory();
        this.tankFactory = new TankFactory();
        running = true;
        this.generatedEnemies = new CopyOnWriteArrayList<>();
        // La primera posicion va a ser una posición valida del mapa cualquiera.
        this.playerPosX = 10;
        this.playerPosY = 20;
    }

    @Override
    public void run() {
        Random rand = new Random();
        while (running) {
            int delay = 3000 + rand.nextInt(3500);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(rand.nextInt(100) < 60) {
                generatedEnemies.add(goblinFactory.createEnemy(setRandomPos(playerPosX), setRandomPos(playerPosY)));
            }
            else {
                generatedEnemies.add(naranjitaFactory.createEnemy(setRandomPos(playerPosX), setRandomPos(playerPosY)));
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (rand.nextInt(100) < 15) {
                generatedEnemies.add(tankFactory.createEnemy(setRandomPos(playerPosX), setRandomPos(playerPosY)));
                // Probabilidad del 15% de generar un tanque
                try {
                    Thread.sleep(10000); // Delay grande por el tank
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Jugador muerto. Eliminando enemigos...");
        try {
            Thread.sleep(100);
            generatedEnemies.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Enemy> getGeneratedEnemies() {
        return generatedEnemies;
    }

    /*
     * Este metodo lo debera manipular el Controller para indicarle al Spawner
     * Cual sera la posicion actual del Player, y spawnear en base a ella.
     */
    public void setPlayerPos(int playerPosX, int playerPosY) {
        this.playerPosX = playerPosX;
        this.playerPosY = playerPosY;
    }

    /*
     * Este metodo lo implementará el thread para elegir una posicion
     * radial a la posicion del Player, es decir, se generan en una circunferencia
     * con centro en el Player.
     */
    public int setRandomPos(int position) {
        Random random = new Random();
        int offset = 600;
        return position + (random.nextBoolean() ? offset : -offset); // Los enemies se generan a offset o -offset de
                                                                     // distancia
    }

    public void stop() {
        running = false;
        freezeEnemies();
        generatedEnemies.clear();
    }

    // Metodo para que los enemigos generados luego de que enzito muera se queden
    // congelados.
    public void freezeEnemies() {
        for (Enemy enemy : generatedEnemies) {
            enemy.setMovSpeed(0);
        }
    }
}
