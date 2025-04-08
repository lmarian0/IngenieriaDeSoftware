package character;

public class Character {
    protected String name;
    protected int hp;
    protected int movSpeed;
    protected int baseDmg;
    protected int posX;
    protected int posY;
    protected int xp;
    protected float attackTime; //How often the character attacks
    protected float attackDuration; //Duration of the character attacks
    protected boolean inAttack;
    protected boolean isAlive;



    public Character(String name, int hp, int movSpeed, int baseDmg, float attackTime, float attackDuration, int posX, int posY, int xp){
        this.inAttack = false;
        this.isAlive = true;
        this.name = name;
        this.hp = hp;
        this.movSpeed = movSpeed;
        this.baseDmg = baseDmg;
        this.attackTime = attackTime;
        this.attackDuration = attackDuration;
        this.posX = posX;
        this.posY = posY;
        this.xp = 0 ;
    }

    //Movement in x
    public void moveRight(){
        this.posX += movSpeed;
    }

    public void moveLeft(){
        this.posX -= movSpeed;
    }

    //Movement in y
    public void moveUp(){
        this.posY += movSpeed;
    }

    public void moveDown(){
        this.posY -= movSpeed;
    }

    //to attack
    protected void attackInit(){
        this.inAttack = true;
    }

    protected void attackEnd(){
        this.inAttack = false;
    }

    //to be damaged
    protected void takeDamage(int damage){
        this.hp -= damage;
        if(hp<=0){
            isAlive = false;
        }
    }

    //Setters to hp, movSpeed, baseDmg, attackTime, attackDuration

    //variable uptHp -> update hp
    public void setHp(int uptHp){
        this.hp = uptHp;
    }

    public void setMovSpeed(int speed){
        this.movSpeed = speed;
    }

    //variable uptAttackTime -> update attack time
    public void setAttackTime(float uptAttackTime){
        this.attackTime = uptAttackTime;
    }

    //variable uptAttackDuration -> update attack duration
    public void setAttackDuration(float uptAttackDuration){
        this.attackDuration = uptAttackDuration;
    }

    //Getters to hp, posX, posY, movSpeed, xp, name, baseDmg
    public String getCharName() {
        return name;
    }

    public int getDmg(){
        return baseDmg;
    }

    public int getHp() {
        return hp;
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
}
