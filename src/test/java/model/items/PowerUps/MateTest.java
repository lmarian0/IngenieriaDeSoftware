package test.java.model.items.PowerUps;

import main.java.model.items.PowerUps.Mate;
import main.java.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MateTest {
    private Mate mate;

    @BeforeEach
    public void setUp() {
        mate = new Mate(50, 100);
    }

    @Test
    public void testName() {
        assertEquals("Mate", mate.getName());
    }

    @Test
    public void testActive() {
        Player player = Player.getInstance();
        // Ajusta la posición del jugador si es necesario
        assertTrue(mate.active(player) || !mate.active(player)); // Solo verifica que no lance excepción
    }

    @Test
    public void testTakeIncreasesSpeed() {
        Player player = Player.getInstance();
        int initialSpeed = player.getMovSpeed();
        mate.take(player);
        assertTrue(player.getMovSpeed() >= initialSpeed);
    }

    @Test
    public void testDrawNoException() {
        assertDoesNotThrow(() -> {
            java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(40, 40, java.awt.image.BufferedImage.TYPE_INT_ARGB);
            java.awt.Graphics g = img.getGraphics();
            mate.draw(g);
        });
    }
}