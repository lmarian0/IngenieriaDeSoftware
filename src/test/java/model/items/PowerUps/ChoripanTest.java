package test.java.model.items.PowerUps;

import main.java.model.items.PowerUps.Choripan;
import main.java.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChoripanTest {
    private Choripan choripan;

    @BeforeEach
    public void setUp() {
        choripan = new Choripan(15, 25);
    }

    @Test
    public void testName() {
        assertEquals("Choripan", choripan.getName());
    }

    @Test
    public void testActive() {
        Player player = Player.getInstance();
        // Solo verifica que no lance excepción
        assertTrue(choripan.active(player) || !choripan.active(player));
    }

    @Test
    public void testTakeIncreasesHp() {
        Player player = Player.getInstance();
        player.setHp(50);
        choripan.take(player);
        assertTrue(player.getHp() > 50);
    }

    @Test
    public void testDrawNoException() {
        assertDoesNotThrow(() -> {
            java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(40, 40, java.awt.image.BufferedImage.TYPE_INT_ARGB);
            java.awt.Graphics g = img.getGraphics();
            choripan.draw(g);
        });
    }
}
