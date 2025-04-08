package character;

public class NPC extends Character{
    private String dialogue;

    public NPC(String name, int hp, int movSpeed, int baseDmg, float attackTime, float attackDuration, int posX, int posY, int xp, String dialogue){
        super(name, hp, movSpeed, baseDmg, attackTime, attackDuration, posX, posY, xp);
        this.dialogue = dialogue;
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
