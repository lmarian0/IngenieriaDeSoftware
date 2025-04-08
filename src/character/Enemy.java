package character;

public class Enemy extends NPC{

    private int agressionLevel;

    public Enemy(String name, int hp, int movSpeed, int baseDmg, float attackTime, float attackDuration, int posX, int posY, int xp, String dialogue, int agressionLevel){
        super(name, hp, movSpeed, baseDmg, attackTime, attackDuration, posX, posY, xp, dialogue);
        this.agressionLevel = agressionLevel;
    }

    public void move(Player p){
        if(getPosX()>p.getPosX()){
            moveLeft();
        }else if(getPosX()<p.getPosX()){
            moveRight();
        }
        
        if(getPosY()>p.getPosY()){
            moveDown();  
        }else if(getPosY()<p.getPosY()){
            moveUp();
        }
    }

    public void attack(Player p){
        p.takeDamage(getDmg());
    }

    public void die(){
        if(getHp()<=0){
            setIsAlive(false);
        }
    }

    //Getters

    public int getAgressionLevel(){
        return agressionLevel;
    }

}
