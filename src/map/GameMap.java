package map;

import constants.Constants;
import javafx.scene.image.Image;

public class GameMap {

    private Image mapImage;
    private int width;
    private int height;
    private int horTiles; //Cantidad de tiles horizontales
    private int verTiles; //Cantidad de tiles verticales
    private int[][] map; // Matriz del mapa

    // Constructor
    public GameMap(Image mapImage) {
        this.mapImage = mapImage;
        this.width = (int) mapImage.getWidth();
        this.height = (int) mapImage.getHeight();
        this.horTiles = width / Constants.TILE_SIZE;
        this.verTiles = height / Constants.TILE_SIZE;

        this.map = new int[horTiles][verTiles];

        setSpawnReg();
        setPlayReg();
        showMap();
    }

    // Define las regiones jugables/caminables
    public void setPlayReg() {
        for (int x = 1; x < horTiles - 1; x++) {
            for (int y = 1; y < verTiles - 1; y++) {
                map[x][y] = Constants.EMPTY;
            }
        }
    }

    // Define las regiones de spawn o bordes
    public void setSpawnReg() {
        for (int x = 0; x < horTiles; x++) {
            for (int y = 0; y < verTiles; y++) {
                if (x == 0 || x == horTiles - 1 || y == 0 || y == verTiles - 1) {
                    map[x][y] = Constants.SPAWN;
                }
            }
        }
    }

    // Muestra el mapa matriceado por consola
    public void showMap() {
        for (int y = 0; y < verTiles; y++) {
            for (int x = 0; x < horTiles; x++) {
                System.out.print(map[x][y] + " ");
            }
            System.out.println();
        }
    }

    // Muestra las medidas del mapa
    public void getMapMeasures() {
        System.out.println("Pixels Width: " + width);
        System.out.println("Pixels Height: " + height);
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
