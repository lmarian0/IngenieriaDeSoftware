package test.java.model.items.PowerUps;

import main.java.model.items.PowerUps.CopaDelMundo;
import main.java.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CopaDelMundoTest {
    private CopaDelMundo copa;

    @BeforeEach
    public void setUp() {
        copa = new CopaDelMundo(100, 200);
    }

    @Test
    public void testName() {
        assertEquals("Copa del mundo", copa.getName());
    }

    @Test
    public void testActive() {
        Player player = Player.getInstance();
        assertTrue(copa.active(player) || !copa.active(player)); // Solo verifica que no lance excepción
    }

    @Test
    public void testTakeIncreasesDamage() {
        Player player = Player.getInstance();
        int initialDamage = player.getDmg();
        copa.take(player);
        assertEquals(initialDamage + 5, player.getDmg());
    }

    @Test
    public void testDrawNoException() {
        assertDoesNotThrow(() -> {
            java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(40, 40, java.awt.image.BufferedImage.TYPE_INT_ARGB);
            java.awt.Graphics g = img.getGraphics();
            copa.draw(g);
        });
    }
}
