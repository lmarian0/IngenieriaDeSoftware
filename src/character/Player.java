package character;
import items.Item;

public class Player extends Character {
    private int level;
    private int coins;
    private int xp;
    private Item weapon;

    public Player(String name, int hp, int movSpeed, int baseDmg, float attackTime, float attackDuration, int posX, int posY, int coins)
    {
        super(name, hp, movSpeed, baseDmg, attackTime, attackDuration, posX, posY);
        this.weapon = new Item(0, "fernet", 1 , 5);
        this.level = 0;
        this.coins = coins;
        this.xp = 0;
    }

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
