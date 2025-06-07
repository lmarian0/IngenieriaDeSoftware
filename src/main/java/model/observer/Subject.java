package main.java.model.observer;

/**
 * Cualquier clase que tenga información que otros quieran observar (como Player o Enemy)
 * debe implementar esta interfaz.
 */

public interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}