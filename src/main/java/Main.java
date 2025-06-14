package main.java;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import main.java.controller.Controller;
import main.java.controller.KeyHandler;
import main.java.model.Player;
import main.java.model.constants.Constants;
import main.java.model.constants.ScreenSettings;
import main.java.model.gameState.*;
import main.java.model.map.GameMap;
import main.java.view.Display;
import main.java.view.MainWindow;
import main.java.view.ui.HUD;

public class Main {
   public static void main(String[] args) {
      
      KeyHandler keyHandler = new KeyHandler();
      Player player = Player.getInstance(); 
      ScreenSettings scSt = ScreenSettings.getInstance();
      scSt.getDevice();

      // Identificar el tamaño de la pantalla
      //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int width = scSt.getScreenWidth();
      int height = scSt.getScreenHeight();
      
      // Crear el mapa del juego
      int horTiles = scSt.getScreenCols();
      int verTiles = scSt.getScreenRows();

      GameMap gameMap = GameMap.getInstance(horTiles, verTiles); // Initialize the game map with 8x6 tiles

      

      // CREAR HUD (se actualiza con la vida y XP del Player)
      HUD hud = new HUD(player);

      Controller controller = new Controller(player, keyHandler);
      Display display = new Display(controller, keyHandler, hud);

      MainWindow window = new MainWindow(display, keyHandler);

      gameMap.showMap(); 
      gameMap.getMapMeasures();
      System.out.println(width + "x" + height);
      System.out.println("Mapa creado con " + horTiles + " columnas y " + verTiles + " filas.");
      System.out.println("Tamaño de pantalla: "  + Constants.TILE_SIZE.getSize() * Constants.SCALE.getSize());
      
      scSt.transformX();

      
      // ESTADOS
      controller.setEstadoActual(new LoadingState(keyHandler, controller));


      Timer timer = new Timer(16, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            controller.update();
            display.repaint();
         }
      });
      timer.start();
   }
   
}