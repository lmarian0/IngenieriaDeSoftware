package main.java.model.Factory;

import main.java.model.Enemy;

public class NaranjitaFactory implements EnemyFactory {

    @Override
    public Enemy createEnemy(int posX, int posY) {
        return new Naranjita(posX, posY);
    }
}
