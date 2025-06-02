package main.java.gameState;

import main.java.model.GameStateModel;

import javax.swing.*;

public abstract class GameState {

    protected GameStateModel context;
    //protected JPanel panel;

    public GameState(GameStateModel context) {
        this.context = context;
    }

    public void doThis() {

    }
}
