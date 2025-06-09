package main.java.model.Factory;

import main.java.model.Enemy;

public class TankFactory implements EnemyFactory {

    @Override
    public Enemy createEnemy(int posX, int posY) {
        return new Tank(posX, posY);
    }
}
