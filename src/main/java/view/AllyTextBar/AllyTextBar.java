package main.java.view.AllyTextBar;
import java.awt.*;
import main.java.model.character.Ally;

public class AllyTextBar {
    private Ally aliado;

    public AllyTextBar(Ally aliado){
        this.aliado = aliado;
    }

    public void draw(Graphics g, int x, int y, int barHeight) {
        if (aliado != null) {
            String mensaje = aliado.speak();
            int barWidth = aliado.speak().length() * 10 + 10; // Ancho de la barra basado en el texto

            // Fondo de la barra
            g.setColor(new Color(0, 0, 0, 180)); // negro semi-transparente
            g.fillRoundRect(x, y, barWidth, barHeight, 20, 20);

            // Borde de la barra
            g.setColor(Color.WHITE);
            g.drawRoundRect(x, y, barWidth, barHeight, 20, 20);

            // Texto del aliado
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(mensaje, x + 20, y + 30);
        }
    }
}
