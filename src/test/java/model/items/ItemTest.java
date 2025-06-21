package test.java.model.items;

import main.java.model.items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    // Subclase dummy para poder instanciar Item
    static class DummyItem extends Item {
        public DummyItem(int id, String itemName, int weight, int itemDamage) {
            super(id, itemName, weight, itemDamage);
        }
    }

    private DummyItem item;

    @BeforeEach
    public void setUp() {
        item = new DummyItem(10, "TestItem", 20, 100);
    }

    @Test
    public void testGetName() {
        assertEquals("TestItem", item.getItemName());
    }

    @Test
    public void testGetPrice(){
        assertEquals(0, item.getItemPrice());
    }
    

    @Test
    public void testSetGetDamage(){
        item.setItemDamage(10);
        assertEquals(10, item.getItemDamage());
    }

    @Test
    public void testGetId() {
        assertEquals(10, item.getId());
    }

    @Test
    public void testGetWeight(){
        assertEquals(20, item.getWeight());
    }
}
