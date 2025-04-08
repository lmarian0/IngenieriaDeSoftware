package core;
import map.Map;
import character.Player;

public class Main {
    public static void main(String[] args) {
        System.out.println("Arrancando");

        Map mapa = new Map(200, 100);

        System.out.println(mapa);
        mapa.getMapMeasures();

        Player p1 = new Player("Osvaldo", 100F, 0.5f, 10.5f, 0,0,0);
        System.out.println(p1.getDmg());
    }
}