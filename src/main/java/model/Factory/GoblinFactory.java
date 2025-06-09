package main.java.model.Factory;
import main.java.model.Enemy;

public class GoblinFactory implements EnemyFactory {

    @Override
    public Enemy createEnemy(int posX, int posY) {
        return new Goblin(posX, posY);
    }
    
}
