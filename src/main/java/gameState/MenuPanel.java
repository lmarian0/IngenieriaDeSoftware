package main.java.gameState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuPanel extends JPanel {

    private BufferedImage backgroundImage;
    private JButton playButton;

    public MenuPanel() {
        setLayout(new BorderLayout());

        try {
            backgroundImage = ImageIO.read(getClass().getResource("/resources/image.png"));
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen del menú principal: " + e.getMessage());
            e.printStackTrace();
            backgroundImage = null;
        }

        playButton = new JButton("PLAY");
        playButton.setFont(new Font("Arial", Font.BOLD, 36));
        playButton.setBackground(new Color(46, 204, 113));
        playButton.setForeground(Color.WHITE);
        playButton.setFocusPainted(false);
        playButton.setBorder(BorderFactory.createLineBorder(new Color(39, 174, 96), 4));
        playButton.setOpaque(true);
        playButton.setPreferredSize(new Dimension(200, 70));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.drawString("No se pudo cargar la imagen de fondo.", getWidth() / 2 - 100, getHeight() / 2);
        }
    }
}
