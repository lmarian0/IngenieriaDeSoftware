package main.java.model;

import main.java.model.character.NPC;
import main.java.model.constants.Constants;
import main.java.model.Player;
import main.java.model.character.Character;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import main.java.model.observer.Observer;
import main.java.model.items.XPOrb;
import main.java.model.observer.Subject;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

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

    private BufferedImage spriteUp1, spriteDown1, spriteLeft1, spriteRight1;
    private BufferedImage spriteUp2, spriteDown2, spriteLeft2, spriteRight2;
    private BufferedImage currentSprite; // Imagen actual del jugador

    private String su1, su2, sd1, sd2, sl1, sl2, sr1, sr2;


    public Enemy(String name, int movSpeed, int posX, int posY, int hp, int baseDmg, int attackTime, int attackDuration, String su1, String su2, String sd1, String sd2, String sl1, String sl2, String sr1, String sr2) {
        super(name, movSpeed ,posX, posY);
        this.hp = hp;
        this.baseDmg = baseDmg;
        this.attackDuration = attackDuration;
        this.attackTime = attackTime;
        try {
            spriteUp1 = ImageIO.read(new File(su1));
            spriteDown1 = ImageIO.read(new File(sd1));
            spriteLeft1 = ImageIO.read(new File(sl1));
            spriteRight1 = ImageIO.read(new File(sr1));
            spriteUp2 = ImageIO.read(new File(su2));
            spriteDown2 = ImageIO.read(new File(sd2));
            spriteLeft2 = ImageIO.read(new File(sl2));
            spriteRight2 = ImageIO.read(new File(sr2));

            this.currentSprite = spriteDown1; // Imagen inicial
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected BufferedImage getCurrentSprite(){
        return currentSprite;
    }
    
    private void stepUp() {setPosY(getPosY()-getMovSpeed());}
    private void stepDown() {setPosY(getPosY()+getMovSpeed());}
    private void stepLeft() {setPosX(getPosX()-getMovSpeed());}
    private void stepRight() {setPosX(getPosX()+getMovSpeed());}
    private void stepUpDiag() {setPosY(getPosY()-(int)(getMovSpeed()/Math.sqrt(2)));}
    private void stepDownDiag() {setPosY(getPosY()+(int)(getMovSpeed()/Math.sqrt(2)));}
    private void stepLeftDiag() {setPosX(getPosX()-(int)(getMovSpeed()/Math.sqrt(2)));}
    private void stepRightDiag() {setPosX(getPosX()+(int)(getMovSpeed()/Math.sqrt(2)));}

    private void logicMove(int playerPosX, int playerPosY){
        if      (playerPosX < getPosX() && playerPosY < getPosY()) {stepLeftDiag(); stepUpDiag();} //MoveLeftUp
        else if (playerPosX < getPosX() && playerPosY > getPosY()) {stepLeftDiag(); stepDownDiag();} //MoveLeftDown
        else if (playerPosX > getPosX() && playerPosY < getPosY()) {stepRightDiag(); stepUpDiag();} //MoveRightUp
        else if (playerPosX > getPosX() && playerPosY > getPosY()) {stepRightDiag(); stepDownDiag();} //MoveRightDown
        else if (playerPosX < getPosX()) stepLeft(); //MoveLeft
        else if (playerPosX > getPosX()) stepRight(); //MoveRight
        else if (playerPosY < getPosY()) stepUp(); //MoveUp
        else if (playerPosY > getPosY()) stepDown(); //MoveDown
    }

    private void logicGraphicMove(int playerPosX, int playerPosY){
        if      (playerPosX < getPosX() && playerPosY < getPosY()) {stepLeftDiag(); stepUpDiag();this.currentSprite = (currentSprite == spriteLeft1) ? spriteLeft2 : spriteLeft1;} //MoveLeftUp
        else if (playerPosX < getPosX() && playerPosY > getPosY()) {stepLeftDiag(); stepDownDiag();this.currentSprite = (currentSprite == spriteLeft1) ? spriteLeft2 : spriteLeft1;} //MoveLeftDown
        else if (playerPosX > getPosX() && playerPosY < getPosY()) {stepRightDiag(); stepUpDiag(); this.currentSprite = (currentSprite == spriteRight1) ? spriteRight2 : spriteRight1;} //MoveRightUp
        else if (playerPosX > getPosX() && playerPosY > getPosY()) {stepRightDiag(); stepDownDiag(); this.currentSprite = (currentSprite == spriteRight1) ? spriteRight2 : spriteRight1;} //MoveRightDown
        else if (playerPosX < getPosX()) {stepLeft(); this.currentSprite = (currentSprite == spriteLeft1) ? spriteLeft2 : spriteLeft1;} //MoveLeft
        else if (playerPosX > getPosX()) {stepRight(); this.currentSprite = (currentSprite == spriteRight1) ? spriteRight2 : spriteRight1;} //MoveRight
        else if (playerPosY < getPosY()) {stepUp(); this.currentSprite = (currentSprite == spriteUp1) ? spriteUp2 : spriteUp1;} //MoveUp
        else if      (playerPosY > getPosY()) {stepDown(); this.currentSprite = (currentSprite == spriteDown1) ? spriteDown2 : spriteDown1;} //MoveDown
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
        if(currentSprite!=null) logicGraphicMove(playerPosX, playerPosY);
        else logicMove(playerPosX, playerPosY);
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
            System.out.println("Un enemigo ha muerto.");
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
        if(!observers.contains(o)) observers.add(o);
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
    public void draw (Graphics g, int offsetXl, int offsetY){
       if (image != null) {
            g.drawImage(image, getPosX(), getPosY(), getWidth()*Constants.SCALE.getSize(), getHeight()*Constants.SCALE.getSize(), null);
        } else {
            g.setColor(Color.YELLOW);
            g.fillRect(getPosX(), getPosY(), getWidth()*Constants.SCALE.getSize(), getHeight()*Constants.SCALE.getSize());
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
