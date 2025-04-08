package map;

public class Map {
    private String nombre;
    private int width;
    private int height;

    public Map(int width, int height) {
        this.nombre = "Mapa 1";
        this.width = width;
        this.height = height;
    }

    public void getMapMeasures () {
        System.out.println( "Ancho: " + width + " Alto: " + height );
    }
}
