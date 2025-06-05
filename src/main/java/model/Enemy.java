package main.java.model;

import main.java.model.character.NPC;
import main.java.model.Player;
import main.java.model.character.Character;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Enemy extends NPC {

    private int baseDmg;
    private float attackTime;
    private float attackDuration;
    private Image image;

    private int dx;
    private int dy;

    // cooldown atack
    private long lastAttackTime = 0;

    public Enemy(String name, int movSpeed, int posX, int posY, int baseDmg, float attackTime, float attackDuration, Image image) {
        super(name, movSpeed ,posX, posY);
        this.baseDmg = baseDmg;
        this.attackDuration = attackDuration;
        this.attackTime = attackTime;
    }

    public void chase(int posX, int posY){

        dx = posX - getPosX();
        dy = posY - getPosY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if(distance > 0 ){
            double vx = (dx / distance) * getMovSpeed();
            double vy = (dy / distance) * getMovSpeed();
            setPosX((int)(getPosX() + vx));
            setPosY((int)(getPosY() + vy));


        }

        
    }

    public void attack(Player p){
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastAttackTime >= attackTime) {
            double dx = p.getPosX() - getPosX();
            double dy = p.getPosY() - getPosY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance < 16 ) {
                p.takeDamage(baseDmg);
                lastAttackTime = currentTime; 
            }
        }
        
    }

    public void die(){
        if(getHp()<=0){
            setIsAlive(false);
        }
    }
    public void draw (Graphics g, int offsetX, int offsetY){
       if (image != null) {
            g.drawImage(image, (int)getPosX() - offsetX, (int)getPosY() - offsetY, getWidth(), getHeight(), null);
        } else {
            g.setColor(Color.YELLOW);
            g.fillRect(getPosX() - offsetX, getPosY() - offsetY, getWidth(), getHeight());
        }
    }
    
    

    public int getWidth() { return 32; }
    public int getHeight() { return 32; }

}
