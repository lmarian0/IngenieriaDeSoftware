 package main.java;

import javax.swing.*;
import main.java.controller.Controller;
import main.java.controller.KeyHandler;
import main.java.model.Enemy;
import main.java.model.Player;
import main.java.model.Factory.EnemyFactory;
import main.java.model.Factory.GoblinFactory;
import main.java.model.constants.Constants;
import main.java.model.constants.MapConstants;
import main.java.model.gameState.MenuState;
import main.java.view.Display;
import main.java.view.MainWindow;
import main.java.model.map.GameMap;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import main.java.view.ui.HUD;

public class Main {
   public static void main(String[] args) {

      System.out.println("Bienvenido a las desventuras del Enzito!");
      boolean screenSelection = false;
      GraphicsDevice device = null;
      Scanner scanner = new Scanner(System.in);
      do {
         try {
            // Aquí podrías implementar una lógica para seleccionar la pantalla
            // Por ejemplo, podrías listar las pantallas disponibles y permitir al usuario elegir
            // En este caso, si el usuario solo cuenta con una pantalla, no será necesario seleccionar una pantalla específica.
            
            if (GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length == 1) {
               // Elige la única pantalla disponible
               device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
               screenSelection = true;
            }
            else{
               // Si hay más de una pantalla, solicita al usuario que elija una
               System.out.println("Ingrese en cual pantalla desea jugar: (1 a "+ GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length +")");
               int n = scanner.nextInt();
               device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[n-1]; // Selecciona la primera pantalla disponible
            } 
            screenSelection = true; // Simulamos que se ha seleccionado una pantalla
         } catch (Exception e) {
             System.out.println("Error al seleccionar la pantalla. Intente nuevamente.");
            scanner.nextLine(); // Limpiar entrada incorrecta
         }
      } while (!screenSelection);
      scanner.close();
      
      KeyHandler keyHandler = new KeyHandler();
      Player player = Player.getInstance(); 
      
      Dimension screenSize = device.getDefaultConfiguration().getBounds().getSize();

      // Identificar el tamaño de la pantalla
     // Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int width = (int) screenSize.getWidth();
      int height = (int) screenSize.getHeight();
      System.out.println("Resolución de pantalla: " + width + "x" + height);
      
      // Crear el mapa del juego
      int horTiles = (width / Constants.SCALE.getSize()) / Constants.TILE_SIZE.getSize();
      int verTiles = (height / Constants.SCALE.getSize()) / Constants.TILE_SIZE.getSize();

      GameMap gameMap = GameMap.getInstance(horTiles, verTiles);

      

      // CREAR HUD (se actualiza con la vida y XP del Player)
      HUD hud = new HUD(player);

      Controller controller = new Controller(player, keyHandler);
      Display display = new Display(controller, keyHandler, hud);

      MainWindow window = new MainWindow(display);

      
      // ESTADOS
      controller.setEstadoActual(new MenuState(keyHandler, controller));


      Timer timer = new Timer(16, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            controller.update();
            display.repaint();
            window.repaint();
         }
      });
      timer.start();
   }
   
}