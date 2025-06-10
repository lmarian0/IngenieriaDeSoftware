package main.java.model.map;

import main.java.model.constants.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameMap {

    private int horTiles; // Cantidad de tiles horizontales
    private int verTiles; // Cantidad de tiles verticales
    private int[][] map; // Matriz del mapa
    private static GameMap SINGLETON_MAP;

    // Constructor
    private GameMap(int width, int height) {

        this.horTiles = width; // El ancho del mapa en tiles
        this.verTiles = height; // La altura del mapa en tiles
        this.map = new int[horTiles][verTiles]; // La matriz de tiles del mapa
        setSpawnReg();
        setPlayReg();
        setObstacles();
    }

    // Método para obtener la instancia única del mapa
    // Utiliza el patrón Singleton para asegurar que solo haya una instancia del
    // mapa
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
        Random rand = new Random();
        for (int x = 1; x < horTiles; x++) {
            for (int y = 1; y < verTiles; y++) {

                // Genera un obstáculo con una probabilidad de 1 en 25
                if (rand.nextInt(25) == Constants.WALL.getSize()) {
                    if (map[x][y] == 0) {
                        if (x >= (horTiles / 2) - 1 && x <= (horTiles / 2) + 1 && y >= (verTiles / 2) - 1
                                && y <= (verTiles / 2) + 1) {
                            // No se generan obstáculos en el centro del mapa
                        } else {
                            map[x][y] = Constants.WALL.getSize();
                        }
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

    public List<int[]> getObsPos() {
        List<int[]> obstaclePositions = new ArrayList<>();

        for (int x = 0; x < horTiles; x++) {
            for (int y = 0; y < verTiles; y++) {
                if (map[x][y] == Constants.WALL.getSize() || map[x][y] == Constants.SPAWN.getSize()) {
                    int obsPosX = x * Constants.TILE_SIZE.getSize() * Constants.SCALE.getSize()
                            - Constants.TILE_SIZE.getSize();
                    int obsPosY = y * Constants.TILE_SIZE.getSize() * Constants.SCALE.getSize()
                            - Constants.TILE_SIZE.getSize();
                    int limitX = obsPosX + Constants.TILE_SIZE.getSize() * (Constants.SCALE.getSize()+1);
                    int limitY = obsPosY + Constants.TILE_SIZE.getSize() * (Constants.SCALE.getSize()+1) ;

                    obstaclePositions.add(new int[] { obsPosX, obsPosY, limitX, limitY });
                }
            }
        }
        return obstaclePositions;
    }

    public void debugObstacles() {
        List<int[]> obstacles = getObsPos();
        System.out.println("Obstáculos generados:");
        for (int[] obs : obstacles) {
            System.out.println("Obstáculo en: (" + obs[0] + ", " + obs[1] + ") - (" + obs[2] + ", " + obs[3] + ")");
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
