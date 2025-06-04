package main.java.model.character;

public class Ally extends NPC{

    private String dialogue;

    public Ally(String name, int movSpeed, int posX, int posY, String dialogue) {
        super(name, movSpeed, posX, posY);
        this.dialogue = "EL PEPE";
    }

    //Setters
    public void setDialogue(String str){
        this.dialogue = str;
    }


    //Getters
    public String speak(){
        return dialogue;
    }

}
