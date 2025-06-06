package main.java.model.Factory;

import main.java.model.Enemy;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.util.Random;

public class EnemySpawner implements Runnable {

    /*
        Clase creada, encargada de spawnear a los enemigos segun el Factory correspondiente.
        Para crear otro generador de Enemigos, tienen que añadirlo como atributo a esta clase.
        Ej: TrapitoFactory, el cual  genere trapitos, y que tenga un metodo como el siguiente:
            generatedEnemies.add(trapitoFactory.createEnemy(10,20));
        Ahora, en el caso de la posicion de generacion, para mi hay que implementar un Singleton en Player,
        el unico Singleton que deberiamos implementar en si, porque acá necesitamos si o si el dato de la
        posX y posY del Player, y como no tenemos forma de meter al Player a esta clase sin acoplar todo,
        conviene invocar a un metodo tipo
        generatedEnemies.add(gobSpawner.createEnemy(Player.getInstance().getPosX + 30, Player.getInstance().getPosY + 30));
        para que se creen a una distancia específica del Player.
     */
    private GoblinFactory goblinFactory;
    private boolean running;
    private List<Enemy> generatedEnemies;

    public EnemySpawner() {
        this.goblinFactory = new GoblinFactory();
        running = true;
        this.generatedEnemies = new CopyOnWriteArrayList<>();
    }

    @Override
    public void run() {
        Random rand = new Random();
        while(running) {
            int delay = 3000 + rand.nextInt(4000);
            try {
                Thread.sleep(delay);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            generatedEnemies.add(goblinFactory.createEnemy(10,20));
        }
    }

    public List<Enemy> getGeneratedEnemies() {
        return generatedEnemies;
    }

    public void stop() {
        running = false;
    }

}
