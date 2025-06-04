package main.java.model;

import main.java.model.character.NPC;
import main.java.model.Player;
import main.java.model.character.Character;

public class Enemy extends NPC {

    private int baseDmg;
    private float attackTime;
    private float attackDuration;

    public Enemy(String name, int movSpeed, int posX, int posY, int baseDmg, float attackTime, float attackDuration) {
        super(name, movSpeed ,posX, posY);
        this.baseDmg = baseDmg;
        this.attackDuration = attackDuration;
        this.attackTime = attackTime;
    }

    public void chase(int posX, int posY){
        if (posX < getPosX()) {
            setPosX(getPosX() - getMovSpeed());
        } else if (posX > getPosX()) {
            setPosX(getPosX() + getMovSpeed());
        }

        if (posY < getPosY()) {
            setPosY(getPosY() - getMovSpeed());
        } else if (posY > getPosY()) {
            setPosY(getPosY() + getMovSpeed());
        }
    }

    public void attack(Player p){
        p.takeDamage(baseDmg);
    }

    public void die(){
        if(getHp()<=0){
            setIsAlive(false);
        }
    }

    public int getWidth() { return 32; }
    public int getHeight() { return 32; }

}
