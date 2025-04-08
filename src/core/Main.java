package core;
import map.Map;
import character.Player;

public class Main {
    public static void main(String[] args) {
        System.out.println("Arrancando");

        Map mapa = new Map(200, 100);

        System.out.println(mapa);
        mapa.getMapMeasures();

        Player p1 = new Player("Osvaldo", 100, 1, 10, 1.2f, 2f, 0, 0, 0);
        System.out.println(p1.getDmg());
    }
}