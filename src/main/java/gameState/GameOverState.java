package main.java.gameState;

import main.java.model.GameStateModel;

public class GameOverState extends GameState {

    private GameOverPanel panel;

    public GameOverState(GameStateModel gameStateModel) {
        super(gameStateModel);
        panel = new GameOverPanel();
    }
    @Override
    public void doThis() {

    }
}
