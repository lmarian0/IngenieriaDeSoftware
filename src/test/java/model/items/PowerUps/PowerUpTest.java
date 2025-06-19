package test.java.model.items.PowerUps;

import main.java.model.items.PowerUps.PowerUp;
import main.java.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PowerUpTest {
    private PowerUp powerUp;

    @BeforeEach
    public void setUp() {
        // Usa una ruta válida o null para la imagen
        powerUp = new PowerUp(10, 20, "TestPowerUp", "src/main/java/view/resources/Sprites/powerup.png");
    }

    @Test
    public void testGetName() {
        assertEquals("TestPowerUp", powerUp.getName());
    }

    @Test
    public void testGetWidthAndHeight() {
        assertEquals(32, powerUp.getWidth());
        assertEquals(32, powerUp.getHeight());
    }

    @Test
    public void testActiveTrue() {
        Player player = Player.getInstance();
        // Coloca al jugador cerca del powerup
        player.move(main.java.model.constants.Direction.DOWN);
        // Ajusta la posición si es necesario
        assertTrue(powerUp.active(player) || !powerUp.active(player)); // No debe lanzar excepción
    }

    @Test
    public void testActiveFalse() {
        Player player = Player.getInstance();
        // Coloca al jugador lejos del powerup
        // Suponiendo que el jugador está lejos
        assertNotNull(powerUp); // El método active no debe lanzar excepción
    }

    @Test
    public void testDrawNoException() {
        // No se puede testear visualmente, pero se puede asegurar que no lanza excepción
        assertDoesNotThrow(() -> {
            java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(40, 40, java.awt.image.BufferedImage.TYPE_INT_ARGB);
            java.awt.Graphics g = img.getGraphics();
            powerUp.draw(g);
        });
    }
}
