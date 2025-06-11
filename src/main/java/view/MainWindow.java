package main.java.view;

import main.java.controller.KeyHandler;
import main.java.model.constants.ScreenSettings;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {

    private ScreenSettings scSt = ScreenSettings.getInstance();


    public MainWindow(Display display, KeyHandler keyHandler) {
        JFrame window = new JFrame("Juego MVC");
        GraphicsDevice device = scSt.getDevice();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setUndecorated(true);
        window.setResizable(false); 
        window.pack();
        device.setFullScreenWindow(window);
        window.add(display);
        
        window.setVisible(true);

        window.addKeyListener(keyHandler);
        window.requestFocusInWindow();
    }
}
