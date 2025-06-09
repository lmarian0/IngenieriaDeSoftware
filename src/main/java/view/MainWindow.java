package main.java.view;


import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow(Display display, GraphicsDevice device) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // Quita la barra de título

        device.setFullScreenWindow(this);

        add(display);
        setVisible(true);
        this.setFocusable(true);
        this.requestFocusInWindow();
        
    }
}
