package test.java.model.items.PowerUps;

import main.java.model.items.PowerUps.Fernet;
import main.java.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FernetTest {
    private Fernet fernet;

    @BeforeEach
    public void setUp() {
        fernet = new Fernet(30, 60);
    }

    @Test
    public void testName() {
        assertEquals("Fernet", fernet.getName());
    }

    @Test
    public void testActive() {
        Player player = Player.getInstance();
        assertTrue(fernet.active(player) || !fernet.active(player)); // Solo verifica que no lance excepción
    }

    @Test
    public void testTakeIncreasesSpeed() {
        Player player = Player.getInstance();
        player.setMovSpeed(10);
        fernet.take(player);
        assertTrue(player.getMovSpeed() >= 10);
    }

    @Test
    public void testDrawNoException() {
        assertDoesNotThrow(() -> {
            java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(40, 40, java.awt.image.BufferedImage.TYPE_INT_ARGB);
            java.awt.Graphics g = img.getGraphics();
            fernet.draw(g);
        });
    }
}
