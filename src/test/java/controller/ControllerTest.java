package test.java.controller;

import main.java.controller.Controller;
import main.java.controller.KeyHandler;
import main.java.model.Player;
import main.java.model.Enemy;
import main.java.model.constants.Direction;
import main.java.model.Factory.EnemySpawner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    private Player player;
    private KeyHandler keyHandler;
    private Controller controller;

    @BeforeEach
    public void setUp() {
        player = Player.getInstance(); // Asegurate que Player tenga constructor público
        keyHandler = new KeyHandler();
        controller = new Controller(player, keyHandler);
    }

    @Test
    public void testHandlePlayerInput_MoveUp() {
        int yInicial = player.getPosY();
        keyHandler.up = true;

        controller.handlePlayerInput();

        assertTrue(player.getPosY() < yInicial);
    }

    @Test
    public void testHandlePlayerInput_MoveRight() {
        int xInicial = player.getPosX();
        keyHandler.right = true;

        controller.handlePlayerInput();

        assertTrue(player.getPosX() > xInicial);
    }

    @Test
    public void testHandlePlayerInput_MoveDown() {
        int xInicial = player.getPosX();
        keyHandler.down = true;

        controller.handlePlayerInput();

        assertTrue(player.getPosY() < xInicial);
    }

    @Test
    public void testHandlePlayerInput_MoveLeft() {
        int xInicial = player.getPosX();
        keyHandler.left = true;

        controller.handlePlayerInput();

        assertTrue(player.getPosX() < xInicial);
    }

}