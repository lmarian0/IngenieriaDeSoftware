package main.java.model.Factory;

import main.java.model.items.PowerUp;

public interface PowerUpFactory {

    PowerUp createPowerUp(int posX, int posY, String type);
    
}
