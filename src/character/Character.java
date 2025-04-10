package character;

public class Character {
    private String name;
    private int hp;
    private int movSpeed;
    private int baseDmg;
    private int posX;
    private int posY;    
    private float attackTime; //How often the character attacks
    private float attackDuration; //Duration of the character attacks
    private boolean inAttack;
    private boolean isAlive;



    public Character(String name, int hp, int movSpeed, int baseDmg, float attackTime, float attackDuration, int posX, int posY){
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

    //Setters to hp, movSpeed, baseDprivatemg, attackTime, attackDuration, isAlive
    public void setDmg(int dmg){
        this.baseDmg = dmg;
    }

    public void setIsAlive(boolean life){
        this.isAlive = life;
    }

    public void setMovSpeed(int speed){
        this.movSpeed = speed;
    }

    //variable uptHp -> update hp
    public void setHp(int uptHp){
        this.hp = uptHp;
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

    public boolean getIsAlive(){
        return isAlive;
    }

    public float getAttackTime(){
        return attackTime;
    }

    public float getAttackDuration(){
        return attackDuration;
    }
}
