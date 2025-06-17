package test.java.model.character;

import main.java.model.character.NPC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class NPCTest {

    static class DummyNPC extends NPC{
        public DummyNPC(String name, int movSpeed, int posX, int posY){
            super(name, movSpeed, posX, posY);
        }
    }

    private DummyNPC npc;

    @BeforeEach
    public void setUp(){
        npc = new DummyNPC("TestDummy", 10, 100, 100);
    }

    @Test
    public void testGetName(){
        assertEquals("TestDummy", npc.getName());
    }


}


