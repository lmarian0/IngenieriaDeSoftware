package main.java.model.character;

public class NPC extends Character {


    public NPC(String name, int movSpeed, int posX, int posY) {
        super(name, 100, movSpeed , posX, posY);
    }

    public String getName(){
        return super.getCharName();
    }

}
