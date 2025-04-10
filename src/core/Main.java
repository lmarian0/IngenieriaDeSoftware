package core;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import map.GameMap;
import character.Player;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        System.out.println("Cargando imagen...");

        // Cargá tu imagen (ruta absoluta o desde resources)
        Image image = new Image("file:src/resources/GOKUMAPAEDO.jpg");

        // Crear el mapa lógico
        GameMap gameMap = new GameMap(image);
        Player p1 = new Player("Osvaldo", 100, 5, 10, 0,0,5, 5, 5);
        System.out.println(p1.getDmg());
    }

    public static void main(String[] args) {
        launch(args); // Lanza JavaFX
    }
}
