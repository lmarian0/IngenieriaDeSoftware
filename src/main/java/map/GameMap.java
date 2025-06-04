package main.java.map;

import main.java.constants.Constants;
import java.util.Random;
public class GameMap{

    private int horTiles; //Cantidad de tiles horizontales
    private int verTiles; //Cantidad de tiles verticales
    private int[][] map; // Matriz del mapa
    public static GameMap SINGLETON_MAP;

    // Constructor
    private GameMap(int width, int height) {
        
        this.horTiles = width; // El ancho del mapa en tiles
        this.verTiles = height; // La altura del mapa en tiles
        this.map = new int[horTiles][verTiles]; //La matriz de tiles del mapa
        setSpawnReg();
        setPlayReg();
        setObstacles();
        showMap();
    }

    // Método para obtener la instancia única del mapa
    // Utiliza el patrón Singleton para asegurar que solo haya una instancia del mapa
    public static GameMap getInstance(int width, int height) {
        if (SINGLETON_MAP == null) {
            synchronized (GameMap.class) {
                if (SINGLETON_MAP == null) {
                    SINGLETON_MAP = new GameMap(width, height);
                }
            }
        }
        return SINGLETON_MAP;
    }

    // Define las regiones jugables/caminables
    public void setPlayReg() {
        for (int x = 1; x < horTiles - 1; x++) {
            for (int y = 1; y < verTiles - 1; y++) {
                map[x][y] = Constants.EMPTY.getSize();
            }
        }
    }

    // Define las regiones de spawn y bordes
    public void setSpawnReg() {
        for (int x = 0; x < horTiles; x++) {
            for (int y = 0; y < verTiles; y++) {
                if (x == 0 || x == horTiles - 1 || y == 0 || y == verTiles - 1) {
                    map[x][y] = Constants.SPAWN.getSize();
                }
            }
        }
    }

    // Define los obstaculos
    public void setObstacles() {   
        for (int x = 1; x < horTiles; x++) {
            for (int y = 1; y < verTiles; y++) {
                Random rand = new Random();
                // Genera un obstáculo con una probabilidad de 1 en 5
                if (rand.nextInt(10) == Constants.WALL.getSize()) {
                    if (map[x][y] == 0) {
                        map[x][y] = Constants.WALL.getSize();
                    }
                }
            }
        }
    }


    // Muestra el mapa matriceado por consola
    public void showMap() {
        for (int y = 0; y < verTiles; y++) {
            for (int x = 0; x < horTiles; x++) {
                System.out.print(getMapMatrix()[x][y] + " ");
            }
            System.out.println();
        }
    }



    // Muestra las medidas del mapa
    public void getMapMeasures() {
        System.out.println("Tiles Width: " + horTiles);
        System.out.println("Tiles Height: " + verTiles);
    }

    // Getters por si los necesitás en otra clase
    public int[][] getMapMatrix() {
        return map;
    }

    public int getTileWidth() {
        return horTiles;
    }

    public int getTileHeight() {
        return verTiles;
    }
}
