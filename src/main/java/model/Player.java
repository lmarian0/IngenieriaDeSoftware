package main.java.model;


import main.java.model.constants.Direction;
import main.java.model.gameState.Observer;
import main.java.model.gameState.Subject;
import main.java.model.map.GameMap;
import main.java.view.Display;
import main.java.controller.KeyHandler;
import main.java.model.items.Item;
import main.java.model.character.Character;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectStreamException;

import main.java.model.gameState.Observer;
import main.java.model.gameState.Subject;

import java.util.ArrayList;
import java.util.List;

public class Player extends Character implements Subject, Observer {
    private int level;
    private int coins;
    private int xp;
    private Item weapon;
    private Direction direction;
    //public static Player SINGLETON_PLAYER;

    private final List<Observer> observers = new ArrayList<>();

    public Player() {
        super("Enzito", 100, 20,  600, 300);
        this.level = 1;
        this.coins = 0;
        this.xp = 0;
        this.direction = Direction.DOWN;
    }

    public void move(Direction direction) {
    GameMap gameMap = GameMap.getInstance(1, 1);
    List<int[]> obstacles = gameMap.getObsPos();

    // Calcular la nueva posición antes de mover al jugador
    int newPosX = posX;
    int newPosY = posY;

    switch (direction) {
        case UP -> newPosY -= getMovSpeed();
        case DOWN -> newPosY += getMovSpeed();
        case LEFT -> newPosX -= getMovSpeed();
        case RIGHT -> newPosX += getMovSpeed();
    }

    // Verificar si la nueva posición colisiona con un obstáculo
    for (int[] obs : obstacles) {
        int obsPosX = obs[0];
        int obsPosY = obs[1];
        int limitX = obs[2];
        int limitY = obs[3];

        if (newPosX + getWidth() > obsPosX && newPosX < limitX &&
            newPosY + getHeight() > obsPosY && newPosY < limitY) {
            
            // Ajustar la posición para evitar que el jugador se quede atrapado
            if (direction == Direction.UP) newPosY = limitY;
            if (direction == Direction.DOWN) newPosY = obsPosY - getHeight();
            if (direction == Direction.LEFT) newPosX = limitX;
            if (direction == Direction.RIGHT) newPosX = obsPosX - getWidth();
        }
    }

    // Actualizar la posición después de la corrección
    posX = newPosX;
    posY = newPosY;
    this.direction = direction;
}


    public void takeDamage(int dmg) {
        setHp(getHp() - dmg);
        System.out.println("Vida actual: " + getHp());  // debug
        notifyObservers();  // <== Esto es lo que actualiza el HealthBar
    }


    public void gainXP(int amount) {
        this.xp += amount;
        notifyObservers();
    }

    public void heal(int amount) {
        setHp(getHp() + amount);
    }

    // Observer: se llama cuando un Enemy notifica que murió
    @Override
    public void update() {
        gainXP(10); // o un valor dinámico
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
        for (Observer o : observers) {
            o.update();
        }
    }

    public void die() {

    }


    //El ataque tiene que ser invocar a un metodo del Enemy al q alcanza con el impacto
    public void attack(Enemy e){
        e.takeDamage(getDmg());
    }

    public void takeWeapon (Item weapon){
        this.weapon=weapon;
    }

    public void levelUp(){
        this.level +=1;
    }

    public void addMoney(int money){
        this.coins += money;
    }

    public void addXp(int exp){
        this.xp += exp;
    }

    public int getDmg(){
        int dmg = (weapon.getItemDamage()+getDmg());
        return dmg;
    }

    public int getLevel(){
        return level;
    }

    public int getMoney(){
        return coins;
    }

    public int getXp(){
        return xp;
    }

    public int getWidth() { return 32; }
    public int getHeight() { return 32; }

    public boolean isAlive() {
        return getHp() > 0;
    }
}
