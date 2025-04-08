package character;
import items.Item;

public class Player extends Character {
    private Item weapon = new Item(0, "stick", 1 , 5);

    public Player(String name, float hp, float movSpeed, float baseDmg, int posX, int posY, int xp)
    {
        super(name, hp, movSpeed, baseDmg, posX, posY, xp);
    }

    public void takeWeapon (Item weapon){
        this.weapon=weapon;
    }

    public float getDmg (){
        float dmg = (weapon.getItemDamage()+baseDmg);
        return dmg;
    }

}
