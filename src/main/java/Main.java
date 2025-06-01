package main.java;

import javax.swing.*;
import main.java.controller.Controller;
import main.java.controller.KeyHandler;
import main.java.model.Enemy;
import main.java.model.Player;
import main.java.view.Display;

import java.util.ArrayList;
import java.util.List;


public class Main {
   public static void main(String[] args)  {
      SwingUtilities.invokeLater(() -> {
         JFrame window = new JFrame("Juego MVC");
         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         window.setResizable(false);

         KeyHandler keyHandler = new KeyHandler();
         Player player = new Player();
         List<Enemy> enemies = new ArrayList<>();
         enemies.add(new Enemy("Pedroni", 2, 100, 20, 0, 0, 0));
         enemies.add(new Enemy("Ayarde", 2, 50, 35, 0, 0, 0));
         enemies.add(new Enemy("Briones", 2, 75, 20, 0, 0, 0));

         Controller controller = new Controller(player, enemies, keyHandler);
         Display display = new Display(controller);
         window.add(display);
         window.pack();
         window.setLocationRelativeTo(null);
         window.setVisible(true);
         window.requestFocusInWindow();

         window.addKeyListener(keyHandler);

         // Bucle de juego
         new Timer(16, e -> {
            controller.update();
            display.repaint();
         }).start();
      });
   }
}
