package main.java.model;


import main.java.model.constants.Direction;
import main.java.view.Display;
import main.java.controller.KeyHandler;
import main.java.model.items.Item;
import main.java.model.character.Character;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Character {
    private int level;
    private int coins;
    private int xp;
    private Item weapon;
    //private Direction direction;

    public Player() {
        super("Enzito",100, 15,  100, 20);
        this.level = 1;
        this.coins = 0;
        this.xp = 0;
        //this.direction = Direction.DOWN;
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP -> posY -= getMovSpeed();
            case DOWN -> posY += getMovSpeed();
            case LEFT -> posX -= getMovSpeed();
            case RIGHT -> posX += getMovSpeed();
        }
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

}
