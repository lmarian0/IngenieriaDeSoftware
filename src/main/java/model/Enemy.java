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
    private float attackTime;
    private float attackDuration;
    private final List<Observer> observers = new ArrayList<>();
    private boolean alive = true;
    private int hp;  // vida base del enemigo

    public Enemy(String name, int movSpeed, int posX, int posY, int hp, int baseDmg, float attackTime, float attackDuration) {
        super(name, movSpeed ,posX, posY);
        this.hp = hp;
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

    public void attack(Player p) {
        if (!p.IsAlive()) return;
        if (p.getHp() <= 0) return;  // No ataca si el player está muerto

        System.out.println("Enemy ataca con daño: " + baseDmg);
        p.takeDamage(baseDmg);
    }

    public void takeDamage(int dmg) {
        hp -= dmg;
        if (hp <= 0 && alive) {
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

}
