package main.java.model;

import main.java.gameState.GameState;

public class GameStateModel {
    GameState context;

    public GameStateModel(GameState initialContext) {
        context = initialContext;
    }

    public void changeState(GameState newState) {
        context = newState;
    }
}
