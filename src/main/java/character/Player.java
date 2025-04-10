package main.java.character;
import main.java.core.GamePanel;
import main.java.core.KeyHandler;
import main.java.items.Item;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Entity {
    private int level;
    private int coins;
    private int xp;
    private Item weapon;
    private GamePanel gp;
    private KeyHandler keyHandler;




    public Player(String name, int hp, int movSpeed, int baseDmg, float attackTime, float attackDuration, int posX, int posY, int coins,GamePanel gp, KeyHandler keyHandler)
    {
        super(name, hp, movSpeed, baseDmg, attackTime, attackDuration, posX, posY);
        this.weapon = new Item(0, "fernet", 1 , 5);
        this.level = 0;
        this.coins = coins;
        this.xp = 0;
        this.gp = gp;
        this.keyHandler = keyHandler;
        getPlayerImage();
        direction = "down"; //Esto luego se hace private y se pone en un metodo de valores default
    }

    public void update(KeyHandler keyH) {
        if(keyH.up == true) {
            direction = "up";
            setPosY(getPosY() - getMovSpeed());
        }

        if(keyH.down == true) {
            direction = "down";
            setPosY(getPosY() + getMovSpeed());
        }

        if(keyH.left == true) {
            direction = "left";
            setPosX(getPosX() - getMovSpeed());
        }

        if(keyH.right == true) {
            direction = "right";
            setPosX(getPosX() + getMovSpeed());
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(this.getPosX(), this.getPosY(), gp.getTILESIZE(), gp.getTILESIZE());

        BufferedImage image = null;
        switch(direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }
        g2d.drawImage(image, this.getPosX(), this.getPosY(), gp.getTILESIZE(), gp.getTILESIZE(), null);
    }

    public void getPlayerImage() {
        try{
            up1 = ImageIO.read(new FileInputStream("src/resources/player/p1_up_1.png"));
            up2 = ImageIO.read(new FileInputStream("src/resources/player/p1_up_2.png"));
            down1 = ImageIO.read(new FileInputStream("src/resources/player/p1_down_1.png"));
            down2 = ImageIO.read(new FileInputStream("src/resources/player/p1_down_2.png"));
            left1 = ImageIO.read(new FileInputStream("src/resources/player/p1_left_1.png"));
            left2 = ImageIO.read(new FileInputStream("src/resources/player/p1_left_2.png"));
            right1 = ImageIO.read(new FileInputStream("src/resources/player/p1_right_1.png"));
            right2 = ImageIO.read(new FileInputStream("src/resources/player/p1_right_2.png"));
            //up1 = ImageIO.read(new FileInputStream("src/resources/player/p1_up_1.png"));
            //up2 = ImageIO.read(getClass().getResourceAsStream("resources/player/p1_up_2.png"));
            //down1 = ImageIO.read(getClass().getResourceAsStream("resources/player/p1_down_1.png"));
            //down2 = ImageIO.read(getClass().getResourceAsStream("resources/player/p1_down_2.png"));
            //left1 = ImageIO.read(getClass().getResourceAsStream("resources/player/p1_left_1.png"));
            //left2 = ImageIO.read(getClass().getResourceAsStream("resources/player/p1_left_2.png"));
            //right1 = ImageIO.read(getClass().getResourceAsStream("resources/player/p1_right_1.png"));
            //right2 = ImageIO.read(getClass().getResourceAsStream("resources/player/p1_right_2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    /*
    public void move(String c){
        switch (c.toLowerCase()) {
            case "w":
                moveUp();
                break;
            case "s":
                moveDown();
                break;
            case "a":
                moveLeft();
                break;
            case "d":
                moveRight();
                break;
        }
    }
    */
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

    //Getters

    @Override
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

}
