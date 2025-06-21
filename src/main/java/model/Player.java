package main.java.model;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import main.java.model.character.Character;
import main.java.model.constants.Direction;
import main.java.model.constants.ScreenSettings;
import main.java.model.items.Item;
import main.java.model.map.GameMap;
import main.java.model.observer.Observer;
import main.java.model.observer.Subject;

public class Player extends Character implements Subject, Observer {
    private int level;
    private int coins;
    private int xp;
    private int dmg;
    private Item weapon;
    private Direction direction;
    private boolean attacking = false;
    private long attackStartTime;
    private final int attackDurationMs = 100;
    private BufferedImage spriteUp1, spriteDown1, spriteLeft1, spriteRight1;
    private BufferedImage spriteUp2, spriteDown2, spriteLeft2, spriteRight2;
    private BufferedImage spriteUpAttack, spriteDownAttack, spriteLeftAttack, spriteRightAttack;
    private BufferedImage currentSprite; // Imagen actual del jugador

    private final List<Observer> observers = new ArrayList<>();
    private static Player SINGLETON_PLAYER;
    private static final ScreenSettings settings = ScreenSettings.getInstance();

    private Player() {
        super("Enzito", 100, 5,  (settings.getScaledWidth(settings)/2), settings.getScaledHeight(settings)/2);
        this.level = 1;
        this.coins = 0;
        this.xp = 0;
        this.dmg = 10;
        this.weapon = null; // Inicialmente sin arma
        this.direction = Direction.DOWN;
        try {
            spriteUp1 = ImageIO.read(new File( "src\\main\\java\\view\\resources\\player\\p1_up_1.png"));
            spriteDown1 = ImageIO.read(new File( "src\\main\\java\\view\\resources\\player\\p1_down_1.png"));
            spriteLeft1 = ImageIO.read(new File("src\\main\\java\\view\\resources\\player\\p1_left_1.png"));
            spriteRight1 = ImageIO.read(new File("src\\main\\java\\view\\resources\\player\\p1_right_1.png"));
            spriteUp2 = ImageIO.read(new File( "src\\main\\java\\view\\resources\\player\\p1_up_2.png"));
            spriteDown2 = ImageIO.read(new File( "src\\main\\java\\view\\resources\\player\\p1_down_2.png"));
            spriteLeft2 = ImageIO.read(new File("src\\main\\java\\view\\resources\\player\\p1_left_2.png"));
            spriteRight2 = ImageIO.read(new File("src\\main\\java\\view\\resources\\player\\p1_right_2.png"));

            spriteUpAttack = ImageIO.read(new File("src\\main\\java\\view\\resources\\player\\p1_up_attack.png"));
            spriteDownAttack = ImageIO.read(new File("src\\main\\java\\view\\resources\\player\\p1_down_attack.png"));
            spriteLeftAttack = ImageIO.read(new File("src\\main\\java\\view\\resources\\player\\p1_left_attack.png"));
            spriteRightAttack = ImageIO.read(new File("src\\main\\java\\view\\resources\\player\\p1_right_attack.png"));

            currentSprite = spriteDown1; // Imagen inicial
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Método para obtener la instancia única del jugador
    public static Player getInstance() {
        if (SINGLETON_PLAYER == null) {
            synchronized (Player.class) {
                if (SINGLETON_PLAYER == null) {
                    SINGLETON_PLAYER = new Player();
                }
            }
        }
        return SINGLETON_PLAYER;
    }

    //Retorna un arreglo con nuevas posiciones en caso movimiento
    private int[] logicMove(Direction direction){
        int newPosX = posX;
        int newPosY = posY;
        switch (direction) {
            case UP -> {newPosY -= getMovSpeed(); currentSprite = (currentSprite == spriteUp1) ? spriteUp2 : spriteUp1;}
            case DOWN -> {newPosY += getMovSpeed(); currentSprite = (currentSprite == spriteDown1) ? spriteDown2 : spriteDown1;}
            case LEFT -> {newPosX -= getMovSpeed(); currentSprite = (currentSprite == spriteLeft1) ? spriteLeft2 : spriteLeft1;}
            case RIGHT -> {newPosX += getMovSpeed(); currentSprite = (currentSprite == spriteRight1) ? spriteRight2 : spriteRight1;}
        }
        return new int[]{newPosX, newPosY};
    }

    //Retorna un arreglo con nuevas posiciones en x e y en caso de colision
    private int[] logicColision(Direction direction, int newPosX, int newPosY){
        GameMap gameMap = GameMap.getInstance(1, 1);
        List<int[]> obstacles = gameMap.getObsPos();
        int x = newPosX;
        int y = newPosY;
        for (int[] obs : obstacles) {
            int obsPosX = obs[0];
            int obsPosY = obs[1];
            int limitX = obs[2];
            int limitY = obs[3];
            if (newPosX + getWidth()/3 > obsPosX && newPosX < limitX- getWidth()*0.8 &&
                newPosY + getHeight()/2 > obsPosY && newPosY < limitY-getHeight()/2) {
                // Ajustar la posición para evitar que el jugador se quede atrapado
                if (direction == Direction.UP) y = limitY-getHeight()/2;
                if (direction == Direction.DOWN) y = obsPosY - getHeight()/2;
                if (direction == Direction.LEFT) x = (int) (limitX - getWidth()*0.7); //Asimetria?
                if (direction == Direction.RIGHT) x = obsPosX - getWidth()/3;
            }
        }
        return new int[]{x, y};
    }

    public void move(Direction direction) {
        // Calcular la nueva posición antes de mover al jugador
        int[] position = logicMove(direction);
        //Se verifica que las nuevas posiciones no den contra un obstaculo, en caso de ser asi se cambian por nuevas teniendo en cuenta la colision
        position = logicColision(direction, position[0], position[1]);
        int newPosX = position[0];
        int newPosY = position[1];
        // Actualizar la posición después de la corrección
        posX = newPosX;
        posY = newPosY;
        this.direction = direction;
    } 
    
    public BufferedImage getCurrentSprite() {
        return currentSprite;
    }

    public void restoreDmg(){
        this.dmg = 10;
    }

    public void restoreSpeed(){
        super.setMovSpeed(5);
    }

    public void addDamage(int damage){
        this.dmg += damage;
    }

    public void takeDamage(int dmg) {
        setHp(getHp() - dmg);
        System.out.println("Vida actual: " + getHp());  // debug
        notifyObservers();  // <== Esto es lo que actualiza el HealthBar
    }

    public void gainXP(int amount) {
        this.xp += amount;
        notifyObservers();
    }

    public void heal(int amount) {
        setHp(getHp() + amount);
    }

    public void addSpeed(int spd){
        super.setMovSpeed(super.getMovSpeed()+spd);
    }

    // Observer: se llama cuando un Enemy notifica que murió
    @Override
    public void update() {
        gainXP(20); // o un valor dinámico
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }


    public void updateSprite() {
        if (attacking) {
            switch (direction) {
                case UP -> currentSprite = spriteUpAttack;
                case DOWN -> currentSprite = spriteDownAttack;
                case LEFT -> currentSprite = spriteLeftAttack;
                case RIGHT -> currentSprite = spriteRightAttack;
            }
        } else {
            switch (direction) {
                case UP -> currentSprite = (currentSprite == spriteUp1) ? spriteUp2 : spriteUp1;
                case DOWN -> currentSprite = (currentSprite == spriteDown1) ? spriteDown2 : spriteDown1;
                case LEFT -> currentSprite = (currentSprite == spriteLeft1) ? spriteLeft2 : spriteLeft1;
                case RIGHT -> currentSprite = (currentSprite == spriteRight1) ? spriteRight2 : spriteRight1;
            }
        }
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
        if (attacking) {
            this.attackStartTime = System.currentTimeMillis();
        }
        updateSprite(); // <- unifica el comportamiento visual del player
    }

    // Llamado en cada update para saber si debe dejar de atacar
    public void updateAttackState() {
        if (attacking && (System.currentTimeMillis() - attackStartTime > attackDurationMs)) {
            setAttacking(false); // <- actualiza el sprite internamente
        }
    }

    //El ataque tiene que ser invocar a un metodo del Enemy al q alcanza con el impacto
    public void attack(Enemy e){
    
        int dx = Math.abs(e.getPosX() - getPosX());
        int dy = Math.abs(e.getPosY() - getPosY());

        if (dx < 32 && dy < 32 && e.getIsAlive()) {
            e.takeDamage(dmg);  // daño de ataque del Player
            System.out.println("¡Ataque exitoso!");
        }
    
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

    public void subXp(int exp){
        this.xp -= exp;
    }

    public int getDmg(){
        if(weapon != null){
            return weapon.getItemDamage() + this.dmg;
        }
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

    public int getWidth() { return 32; }
    public int getHeight() { return 32; }

    public boolean isAlive() {
        return getHp() > 0;
    }
}
