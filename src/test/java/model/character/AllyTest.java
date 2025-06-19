package test.java.model.character;

import main.java.model.character.Ally;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AllyTest {
    private Ally ally;

    @BeforeEach
    public void setUp() {
        // Usa una ruta válida o null para la imagen
        ally = new Ally("Sergio", "Soy sergio, tu aliado", "src/main/java/view/resources/Aliades/sergio.png");
    }

    @Test
    public void testGetName() {
        assertEquals("Sergio", ally.getName());
    }

    @Test
    public void testSpeak() {
        assertEquals("Soy sergio, tu aliado", ally.speak());
    }

    @Test
    public void testSetDialogue() {
        ally.setDialogue("Nuevo diálogo");
        assertEquals("Nuevo diálogo", ally.speak());
    }

    @Test
    public void testAddClkDuracion() {
        int initial = ally.getClkDuration();
        ally.addClkDuracion();
        assertEquals(initial + 1, ally.getClkDuration());
    }

    @Test
    public void testImageNotNullOrNull() {
        // No falla si la imagen es null o no, solo prueba que no explote el constructor
        assertNotNull(ally);
    }
}