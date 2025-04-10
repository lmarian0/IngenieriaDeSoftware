package main.java.core;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {
       JFrame window = new JFrame();
       window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       window.setResizable(true);
       window.setTitle("Las desventuras de Enzito");

       GamePanel gp = new GamePanel();
       window.add(gp);
       window.pack();
       window.setLocationRelativeTo(null);
       window.setVisible(true);
       gp.startGameThread();
    }
}
