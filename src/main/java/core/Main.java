package main.java.core;

import javax.swing.*;

import javafx.scene.image.Image;
import main.java.map.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.java.character.*;


public class Main {
   public static void main(String[] args)  {
      JFrame window = new JFrame();
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setResizable(true);
      window.setTitle("Las desventuras de Enzito");


      //       Image mapImage = new Image("file:src/main/resources/tiles/grass.png");
      //       GameMap map = new GameMap(mapImage);
      // map.showMap();

      Display gp = new Display();
      window.add(gp);
      window.pack();
      window.setLocationRelativeTo(null);
      window.setVisible(true);
      gp.startGameThread();
   }
}
