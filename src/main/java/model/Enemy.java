package main.java.model;

import main.java.model.character.NPC;
import main.java.model.Player;
import main.java.model.character.Character;

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

    public Enemy(int id, String name, int movSpeed, int posX, int posY, int hp, int baseDmg, int attackTime, int attackDuration) {
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
        if (!p.IsAlive()) return;
        if (p.getHp() <= 0) return;  // No ataca si el player está muerto
        System.out.println("Enemy [" + id + "] ataca con daño: " + baseDmg);
        p.takeDamage(baseDmg);
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
