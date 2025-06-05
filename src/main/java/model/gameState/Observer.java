package main.java.model.gameState;

/**
 * Cualquier clase que quiera ser notificada de un cambio (como HUD, XPBar, HealthBar)
 * debe implementar esta interfaz.
 */

public interface Observer {
    void update();
}
