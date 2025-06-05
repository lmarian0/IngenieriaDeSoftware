package main.java.model.character;

import java.awt.image.BufferedImage;

public abstract class Character {
    private String name;
    private int hp;
    private int movSpeed;
    protected int posX;
    protected int posY;
    private boolean inAttack;
    private boolean isAlive;

    public int spriteCounter;
    public int spriteNum;

    public Character(String name, int hp, int movSpeed, int posX, int posY) {
        this.inAttack = false;
        this.isAlive = true;
        this.name = name;
        this.hp = hp;
        this.movSpeed = movSpeed;
        this.posX = posX;
        this.posY = posY;
    }

    public Character(String name, int movSpeed, int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        this.movSpeed = movSpeed;
    }


    //to attack
    protected void attackInit(){
        this.inAttack = true;
    }

    protected void attackEnd(){
        this.inAttack = false;
    }

    //to be damaged
    public void takeDamage(int damage){
        this.hp -= damage;
        if(hp<=0){
            isAlive = false;
        }
    }

    public void setIsAlive(boolean life){
        this.isAlive = life;
    }

    public void setMovSpeed(int speed){
        this.movSpeed = speed;
    }

    //variable uptHp -> update hp
    public void setHp(int uptHp) {
        this.hp = Math.max(0, uptHp); // evita valores negativos
    }

    public int getHp() {
        return this.hp;
    }

    //Getters to hp, posX, posY, movSpeed, xp, name, baseDmg
    public String getCharName() {
        return name;
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public int getMovSpeed() {
        return movSpeed;
    }

    public boolean getInAttack(){
        return inAttack;
    }

    public boolean IsAlive(){
        return this.isAlive;
    }

    protected void setPosX(int posX) {
        this.posX = posX;
    }

    protected void setPosY(int posY) {
        this.posY = posY;
    }
}
