package test.java.model.items;

import main.java.model.items.Drop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DropTest {

    // Subclase dummy para poder instanciar Drop
    static class DummyDrop extends Drop {
        public DummyDrop(int posX, int posY) {
            super(posX, posY);
        }
    }

    private DummyDrop drop;

    @BeforeEach
    public void setUp() {
        drop = new DummyDrop(5, 15);
    }

    @Test
    public void testGetPosXandY() {
        assertEquals(5, drop.getPosX());
        assertEquals(15, drop.getPosY());
    }
}