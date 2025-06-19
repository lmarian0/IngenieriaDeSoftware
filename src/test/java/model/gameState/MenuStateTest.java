package test.java.model.gameState;

import org.junit.Test;
import static org.junit.Assert.*;
import main.java.controller.Controller;
import main.java.controller.KeyHandler;
import main.java.model.Player;
import main.java.model.gameState.MenuState;
import main.java.model.gameState.PlayingState;

public class MenuStateTest {
    @Test
    public void testSpaceKeyChangesState() {
        KeyHandler keyHandler = new KeyHandler();
        Player player = Player.getInstance();
        Controller controller = new Controller(player, keyHandler);
        MenuState menuState = new MenuState(keyHandler, controller);
        controller.setEstadoActual(menuState);

        keyHandler.space = true;
        menuState.update();

        assertTrue(controller.getEstadoActual() instanceof PlayingState);
    }
}