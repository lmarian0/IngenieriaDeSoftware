package main.java.model.Factory;

import main.java.model.Enemy;

public interface EnemyFactory {
    Enemy createEnemy(int posX , int posY);
}
