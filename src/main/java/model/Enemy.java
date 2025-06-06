package main.java.model;

import main.java.model.character.NPC;
import main.java.model.Player;
import main.java.model.character.Character;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import main.java.model.gameState.Subject;
import main.java.model.gameState.Observer;
import main.java.model.items.XPOrb;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends NPC implements Subject {

    private int baseDmg;
    private int attackTime;
    private int attackDuration;
    private final List<Observer> observers = new ArrayList<>();
    private boolean alive = true;
    private int hp;  // vida base del enemigo
    private int id;

    private Image image;
    // cooldown atack
    private long lastAttackTime = 0;

    public Enemy(String name, int movSpeed, int posX, int posY, int hp, int baseDmg, int attackTime, int attackDuration, Image image) {
        super(name, movSpeed ,posX, posY);
        this.id = id;
        this.hp = hp;
        this.baseDmg = baseDmg;
        this.attackDuration = attackDuration;
        this.attackTime = attackTime;
    }

    private void logicMove(int playerPosX, int playerPosY){
        if      (playerPosX < getPosX()) setPosX(getPosX() - getMovSpeed());
        else if (playerPosX > getPosX()) setPosX(getPosX() + getMovSpeed());
        if      (playerPosY < getPosY()) setPosY(getPosY() - getMovSpeed());
        else if (playerPosY > getPosY()) setPosY(getPosY() + getMovSpeed());
    }

    public void chase(int playerPosX, int playerPosY, List<Enemy> enemies) {
        for(Enemy enemy : enemies){
            if (enemy != this && isCollidingWith(enemy)) {
                if      (getPosX() - enemy.getPosX()>0) setPosX(getPosX() + getMovSpeed());
                else    setPosX(getPosX() - getMovSpeed());
                if      (getPosY() - enemy.getPosY()>0) setPosY(getPosY() + getMovSpeed());
                else    setPosY(getPosY() - getMovSpeed());
                return;
            }
        }
        logicMove(playerPosX, playerPosY);
    }

    public boolean isCollidingWith(Enemy other){
        int dx = Math.abs(getPosX() - other.getPosX());
        int dy = Math.abs(getPosY() - other.getPosY());
        return dx<=other.getWidth() && dy<= other.getHeight();
    }

    public void attack(Player p) {
        
        long currentTime = System.currentTimeMillis();
        if (!alive) return;
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

    public void takeDamage(int dmg) {
        hp -= dmg;
        if (hp <= 0 && alive) {
            System.out.println("Enemy [" + id + "] ha muerto.");
            die();  // si muere, se notifica al Player (Observer)
        }
    }

    public void die() {
        this.alive = false;
        XPOrb orb = new XPOrb(posX, posY, 5); //Hay que ver como hacer que la View se entere de la existencia para dibujarlo
        notifyObservers();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        if (!alive) {
            for (Observer o : observers) {
                o.update();
            }
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

    public boolean getIsAlive() {
        return alive;
    }

    public int getHp() {
        return hp;
    }

    public int gepId() { return id; }

}
