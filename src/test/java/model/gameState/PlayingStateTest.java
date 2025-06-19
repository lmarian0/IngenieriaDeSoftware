package test.java.model.gameState;

import org.junit.Test;
import static org.junit.Assert.*;
import main.java.controller.Controller;
import main.java.controller.KeyHandler;
import main.java.model.Player;
import main.java.model.gameState.PlayingState;
import main.java.model.gameState.GameOverState;

public class PlayingStateTest {
    @Test
    public void testPlayerDeathChangesToGameOverState() {
        KeyHandler keyHandler = new KeyHandler();
        Player player = Player.getInstance();
        Controller controller = new Controller(player, keyHandler);
        PlayingState playingState = new PlayingState(keyHandler, controller);
        controller.setEstadoActual(playingState);

        // Simula que el jugador muere
        player.setHp(0);
        playingState.update();
        // Verifica que el estado actual cambie a GameOverState
        assertTrue(controller.getEstadoActual() instanceof GameOverState);
    }
}