package main.java;

import javax.swing.*;
import main.java.controller.Controller;
import main.java.controller.KeyHandler;
import main.java.model.Enemy;
import main.java.model.Player;
import main.java.view.Display;
import main.java.view.MainWindow;
import main.java.model.map.GameMap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.java.view.ui.HUD;


public class   Main {
   public static void main(String[] args) {

      Random rand = new Random();
      int cantidadEnemigos = 10;
      int separacionMinima = 100;

      ArrayList<int[]> posicionesUsadas = new ArrayList<>();
      KeyHandler keyHandler = new KeyHandler();
      Player player = new Player();
      List<Enemy> enemies = new ArrayList<>();

      for (int i = 0; i < cantidadEnemigos; i++) {
         int x, y;
         boolean posicionValida;

         do {
            posicionValida = true;
            x = rand.nextInt(1920 - 32);  // considerando tamaño del enemy
            y = rand.nextInt(960 - 32);

            for (int[] pos : posicionesUsadas) {
               int dx = Math.abs(pos[0] - x);
               int dy = Math.abs(pos[1] - y);
               if (dx < separacionMinima && dy < separacionMinima) {
                  posicionValida = false;
                  break;
               }
            }

         } while (!posicionValida);

         int baseDmg = rand.nextInt(5) + 1;  // daño aleatorio entre 1 y 5
         posicionesUsadas.add(new int[]{x, y});
         enemies.add(new Enemy(i,"Enemy" + i, 2, x, y, 5, baseDmg, 10,0));
      }
      
      // VINCULAR: cada enemy notifica al player cuando muere
      for (Enemy enemy : enemies) {
         enemy.addObserver(player);  // Player gana XP
      }

      // CREAR HUD (se actualiza con la vida y XP del Player)
      HUD hud = new HUD(player);


      Controller controller = new Controller(player, enemies, keyHandler);
      Display display = new Display(controller, keyHandler, hud);
      MainWindow window = new MainWindow(display);

      GameMap gameMap = GameMap.getInstance(display.getScreenCol(), display.getScreenRow()); // Initialize the game map with 8x6 tiles

      gameMap.showMap();
      gameMap.getMapMeasures();

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