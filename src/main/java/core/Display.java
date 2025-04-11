package main.java.core;

import main.java.character.Player;
import javax.swing.*;
import java.awt.*;

public class Display extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int ORIGINAL_TILE_SIZE = 32; // 32x32 Tile
    final int SCALE = 3;
    final int FPS = 60;
    private final int TILESIZE = ORIGINAL_TILE_SIZE * SCALE; // 96x96 Tile Size
    final int MAXSCREENCOL = 8;
    final int MAXSCREENROW = 6;
    final int SCREENWIDTH = TILESIZE * MAXSCREENCOL;
    final int SCREENHEIGHT = TILESIZE * MAXSCREENROW;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player enzito = new Player("Enzito",100, 5, 5, 3, 1, 100, 100, 100, this, keyH);

    public Display() {
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() { // Cuando creamos un hilo, este llama al metodo run() inmediatamente
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();

        while(gameThread != null) {

            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if(delta >= 1) {
                //Update: Actualiza la informacion como la posicion del personaje.
                update();

                //Draw: Dibuja la pantalla con la informacion actualizada.
                repaint();

                delta--;
            }
        }
    }

    public void update() {
        enzito.update(keyH);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        enzito.draw(g2d);
        g2d.dispose();
    }

    public int getTILESIZE() {
        return TILESIZE;
    }
}
