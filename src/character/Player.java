package character;
import items.Item;

public class Player extends Character {
    private Item weapon = new Item(0, "stick", 1 , 5);

    public Player(String name, int hp, int movSpeed, int baseDmg, float attackTime, float attackDuration, int posX, int posY, int xp)
    {
        super(name, hp, movSpeed, baseDmg, attackTime, attackDuration, posX, posY, xp);
    }

    public void takeWeapon (Item weapon){
        this.weapon=weapon;
    }

    public int getDmg (){
        int dmg = (weapon.getItemDamage()+baseDmg);
        return dmg;
    }

}
