package test.java.model.character;

import main.java.model.character.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {

    // Subclase dummy para poder instanciar Character
    static class DummyCharacter extends Character {
        public DummyCharacter(String name, int hp, int movSpeed, int posX, int posY) {
            super(name, hp, movSpeed, posX, posY);
        }
    }

    private DummyCharacter character;

    @BeforeEach
    public void setUp() {
        character = new DummyCharacter("Test", 100, 5, 10, 20);
    }

    @Test
    public void testGetCharName() {
        assertEquals("Test", character.getCharName());
    }

    @Test
    public void testGetHp() {
        assertEquals(100, character.getHp());
    }

    @Test
    public void testSetHp() {
        character.setHp(50);
        assertEquals(50, character.getHp());
        character.setHp(-10);
        assertEquals(0, character.getHp()); // No puede ser negativo
    }

    @Test
    public void testGetPosXandY() {
        assertEquals(10, character.getPosX());
        assertEquals(20, character.getPosY());
    }

    @Test
    public void testSetMovSpeed() {
        character.setMovSpeed(8);
        assertEquals(8, character.getMovSpeed());
    }

    @Test
    public void testTakeDamage() {
        character.takeDamage(30);
        assertEquals(70, character.getHp());
        character.takeDamage(100);
        assertFalse(character.IsAlive());
    }

    @Test
    public void testSetIsAlive() {
        character.setIsAlive(false);
        assertFalse(character.IsAlive());
        character.setIsAlive(true);
        assertTrue(character.IsAlive());
    }
}