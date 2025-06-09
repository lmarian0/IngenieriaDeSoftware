package main.java.model.PowerUpFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import main.java.model.Enemy;
import main.java.model.character.Ally;
import main.java.model.items.PowerUps.Choripan;
import main.java.model.items.PowerUps.CopaDelMundo;
import main.java.model.items.PowerUps.Fernet;
import main.java.model.items.PowerUps.Mate;
import main.java.model.items.PowerUps.PowerUp;
import main.java.model.map.GameMap;
import main.java.model.constants.Constants;

public class PowerUpFactory implements Runnable{
    private Ally aliado;
    private GameMap mapa = GameMap.getInstance(0, 0);
    private Random random = new Random();
    private boolean active;
    private List<Power> generatedPowerUps;
    private int TILESIZE = Constants.TILE_SIZE.getSize();

    public PowerUpFactory(Ally aliado){
        this.aliado = aliado;
        this.active = false;
        this.generatedPowerUps = new CopyOnWriteArrayList<>();
    }

    @Override
    public void run(){
        try {
            while(true) {
                if(active){
                    Power powerUp;
                    int[] position = generateValidPosition();
                    switch (aliado.getName()) {
                        case "Sergio":
                            powerUp = new Choripan(position[0], position[1]);
                            break;
                        case "JuanMa":
                            powerUp = new Mate(position[0], position[1]);
                            break;
                        case "Nikito":
                            powerUp = new Fernet(position[0], position[1]);
                            break;
                        default:
                            powerUp = new CopaDelMundo(position[0], position[1]);
                            break;
                    }
                    
                    Thread.sleep(300);
                    generatedPowerUps.add(powerUp);
                }else{
                    generatedPowerUps.clear();
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Generación de PowerUps interrumpida.");
        }
    }

    private int[] generateValidPosition(){
        int x, y;
        do {
            x = TILESIZE*2 + random.nextInt((mapa.getTileWidth()-2)*TILESIZE*2-TILESIZE);
            y = TILESIZE*2 + random.nextInt((mapa.getTileHeight()-2)*TILESIZE*2-TILESIZE);
        } while (!isValidPosition(x, y));
        return new int[]{x, y};
    }

    private boolean isValidPosition(int x, int y){
        List<int[]> obstaclePositions = mapa.getObsPos();
        for (int[] obstacle : obstaclePositions) {
            int dx = Math.abs(x-obstacle[0]);
            int dy = Math.abs(y-obstacle[1]);
            if (dx<=TILESIZE*2 && dy<=TILESIZE*2) {
                return false; // La posición está ocupada por un obstáculo
            }
        }
        return true;
    }

    public void setAlly(Ally ally){
        if(ally != null){
            this.aliado = ally;
        }
    }

    public void setProduction(boolean act){
        this.active = act;
    }

    public boolean getProduction(){
        return this.active;
    }
    
    public List<Power> getGeneratedPowerUps() {
        return generatedPowerUps;
    }

    public void stop() {
        setProduction(false);
        setAlly(null);
        generatedPowerUps.clear();
    }

    public void delGeneratedPowerUps(Power powerUp){
        this.generatedPowerUps.remove(powerUp);
    }
    
}
