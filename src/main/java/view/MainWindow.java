package main.java.view;

import main.java.controller.KeyHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {

    public MainWindow(Display display) {
        JFrame window = new JFrame("Juego MVC");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.add(display);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
