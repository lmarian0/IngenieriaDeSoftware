package character;
import items.Item;

public class Player extends Character {
    private int level;
    private int coins;
    private Item weapon;

    public Player(String name, int hp, int movSpeed, int baseDmg, float attackTime, float attackDuration, int posX, int posY, int xp)
    {
        super(name, hp, movSpeed, baseDmg, attackTime, attackDuration, posX, posY, xp);
        this.weapon = new Item(0, "fernet", 1 , 5);
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

    public int getDmg(){
        int dmg = (weapon.getItemDamage()+getDmg());
        return dmg;
    }

}
