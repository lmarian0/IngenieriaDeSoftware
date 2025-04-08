package character;

public class Character {
    protected String name;
    protected float hp;
    protected float movSpeed;
    protected float baseDmg;
    protected int posX;
    protected int posY;
    protected int xp;

    public Character(String name, float hp, float movSpeed, float baseDmg, int posX, int posY, int xp) {
        this.name = name;
        this.hp = hp;
        this.movSpeed = movSpeed;
        this.baseDmg = baseDmg;
        this.posX = posX;
        this.posY = posY;
        this.xp = 0 ;
    }

    public String getCharName() {
        return name;
    }

    public float getHp() {
        return hp;
    }

    public void getPos(int x, int y) {
        System.out.println("X:"+x + "Y:"+y);
    }

}
