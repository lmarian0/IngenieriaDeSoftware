package main.java.model.strategy;

import main.java.model.Player;

public class Mate extends PowerUp {

    public Mate() {

    }
    @Override
    public void takePowerUp(Player player) {
        player.heal(30);
        player.setMovSpeed(0);
        //Dejamos pasar unos segundos
        player.setMovSpeed(15);
    }
}
