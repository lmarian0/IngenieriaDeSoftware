package main.java.view;

import main.java.controller.Controller;
import main.java.model.map.GameMap;
import main.java.model.Enemy;
import main.java.model.Player;
import main.java.model.constants.Constants;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.*;
import java.awt.*;

import main.java.view.ui.HUD;

public class Display extends JPanel {

    // SCREEN SETTINGS
    private final int FPS = 60;
    private final int TILESIZE = Constants.TILE_SIZE.getSize() * Constants.SCALE.getSize() ; // 96x96 Tile Size
    private final int MAXSCREENCOL = 20;
    private final int MAXSCREENROW = 10;
    private final int SCREENWIDTH = TILESIZE * MAXSCREENCOL;
    private final int SCREENHEIGHT = TILESIZE * MAXSCREENROW;
    private final Controller controller;
    private final HUD hud;

    // Imagen
    
    public Display(Controller controller, KeyListener keyHandler, HUD hud) {
        this.controller = controller;
        this.hud = hud;
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
        this.requestFocusInWindow();

    }

    public int getScreenRow() {
        return MAXSCREENROW;
    }
    public int getScreenCol() {
        return MAXSCREENCOL;
    }

    @Override
    protected void paintComponent(Graphics g) { 
        super.paintComponent(g);
        controller.drawEstadoActual(g);
        
    }
}
