package main.java;

import javax.swing.*;
import main.java.controller.Controller;
import main.java.controller.KeyHandler;
import main.java.model.Enemy;
import main.java.model.Player;
import main.java.model.Factory.EnemyFactory;
import main.java.model.Factory.GoblinFactory;
import main.java.view.Display;
import main.java.view.MainWindow;
import main.java.model.map.GameMap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import main.java.view.ui.HUD;


public class   Main {
   public static void main(String[] args) {

      KeyHandler keyHandler = new KeyHandler();
      Player player = new Player();
      List<Enemy> enemies = new ArrayList<>();

      // VINCULAR: cada enemy notifica al player cuando muere
      for (Enemy enemy : enemies) {
         enemy.addObserver(player);  // Player gana XP
      }

      // CREAR HUD (se actualiza con la vida y XP del Player)
      HUD hud = new HUD(player);


      Controller controller = new Controller(player, keyHandler);
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