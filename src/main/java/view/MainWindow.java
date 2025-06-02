package main.java.view;

import main.java.controller.KeyHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Ventana principal del programa.
 */
public class MainWindow {

    public MainWindow(View display, KeyHandler keyHandler) {
        JFrame window = new JFrame("Juego MVC");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.add(display);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.requestFocusInWindow();

        window.addKeyListener(keyHandler);
    }
}
