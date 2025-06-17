package test.java.model;

import main.java.model.Enemy;
import main.java.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class EnemyTest {
    private Enemy enemy;
    private Player player;

    @BeforeEach
    public void setUp() {
        // Usa rutas válidas o mocks para las imágenes
        enemy = new Enemy("TestEnemy", 2, 100, 100, 50, 10, 1000, 500,
                "src/main/java/view/resources/naranjita/naranjita_up1.png",
                "src/main/java/view/resources/naranjita/naranjita_up1.png",
                "src/main/java/view/resources/naranjita/naranjita_up1.png",
                "src/main/java/view/resources/naranjita/naranjita_up1.png",
                "src/main/java/view/resources/naranjita/naranjita_up1.png",
                "src/main/java/view/resources/naranjita/naranjita_up1.png",
                "src/main/java/view/resources/naranjita/naranjita_up1.png",
                "src/main/java/view/resources/naranjita/naranjita_up1.png");
        player = Player.getInstance();
    }

    @Test
    public void testTakeDamage() {
        int initialHp = enemy.getHp();
        enemy.takeDamage(10);
        assertEquals(initialHp - 10, enemy.getHp());
    }

    @Test
    public void testDie() {
        enemy.takeDamage(1000);
        assertFalse(enemy.getIsAlive());
    }

    @Test
    public void testAttackPlayer() {
        int initialHp = player.getHp();
        enemy.attack(player);
        assertTrue(player.getHp() <= initialHp);
    }

    @Test
    public void testChase() {
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);
        int initialX = enemy.getPosX();
        int initialY = enemy.getPosY();
        enemy.chase(initialX + 50, initialY + 50, enemies);
        assertNotEquals(initialX, enemy.getPosX());
        assertNotEquals(initialY, enemy.getPosY());
    }

    @Test
    public void testIsCollidingWith() {
        Enemy other = new Enemy("Other", 2, 100, 100, 50, 10, 1000, 500,
                "src/main/java/view/resources/naranjita/naranjita_up1.png",
                "src/main/java/view/resources/naranjita/naranjita_up1.png",
                "src/main/java/view/resources/naranjita/naranjita_up1.png",
                "src/main/java/view/resources/naranjita/naranjita_up1.png",
                "src/main/java/view/resources/naranjita/naranjita_up1.png",
                "src/main/java/view/resources/naranjita/naranjita_up1.png",
                "src/main/java/view/resources/naranjita/naranjita_up1.png",
                "src/main/java/view/resources/naranjita/naranjita_up1.png");
        assertTrue(enemy.isCollidingWith(other));
    }
}