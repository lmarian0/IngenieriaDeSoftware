package test.java.model;

import main.java.model.Player;
import main.java.model.items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = Player.getInstance();
        player.setHp(100); // Reinicia la vida para cada test
        player.addXp(-player.getXp()); // Reinicia XP
        player.addMoney(-player.getMoney()); // Reinicia monedas
        while (player.getLevel() > 1) player.levelUp(); // Si tienes método para setear nivel, úsalo
        player.restoreDmg();
        player.restoreSpeed();
    }

    @Test
    public void testSingleton() {
        Player p1 = Player.getInstance();
        Player p2 = Player.getInstance();
        assertSame(p1, p2);
    }

    @Test
    public void testTakeDamage() {
        int initialHp = player.getHp();
        player.takeDamage(10);
        assertEquals(initialHp - 10, player.getHp());
    }

    @Test
    public void testGainXP() {
        int initialXp = player.getXp();
        player.gainXP(5);
        assertEquals(initialXp + 5, player.getXp());
    }

    @Test
    public void testLevelUp() {
        int initialLevel = player.getLevel();
        player.levelUp();
        assertEquals(initialLevel + 1, player.getLevel());
    }

    @Test
    public void testHeal() {
        player.setHp(50);
        player.heal(20);
        assertEquals(70, player.getHp());
    }

    @Test
    public void testAddMoney() {
        int initialMoney = player.getMoney();
        player.addMoney(10);
        assertEquals(initialMoney + 10, player.getMoney());
    }

    @Test
    public void testAddAndSubXp() {
        player.addXp(20);
        assertTrue(player.getXp() >= 20);
        player.subXp(10);
        assertTrue(player.getXp() >= 10);
    }

    @Test
    public void testIsAlive() {
        player.setHp(1);
        assertTrue(player.isAlive());
        player.setHp(0);
        assertFalse(player.isAlive());
    }

    @Test
    public void testTakeWeapon() {
        Item weapon = null; // Usa un mock o una instancia real si tienes una clase concreta
        assertDoesNotThrow(() -> player.takeWeapon(weapon));
    }

    @Test
    public void testRestoreDmgAndSpeed() {
        player.addDamage(10);
        player.addSpeed(5);
        player.restoreDmg();
        player.restoreSpeed();
        assertEquals(10, player.getDmg());
        assertEquals(5, player.getMovSpeed());
    }
}