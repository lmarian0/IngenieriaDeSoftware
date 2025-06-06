package main.java.model.strategy;

import main.java.model.Player;

public class Choripan extends PowerUp {

    public Choripan() {

    }

    @Override
    public void takePowerUp(Player player) {
        player.heal(50);
    }
}
