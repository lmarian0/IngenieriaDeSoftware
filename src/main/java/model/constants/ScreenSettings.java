package main.java.model.constants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Scanner;

public class ScreenSettings {

    private boolean screenSelection = false;
    private GraphicsDevice device = null;
    private Scanner scanner = new Scanner(System.in);
    private static ScreenSettings ScreenSINGLETON; 

    private ScreenSettings() {
        this.device = getDevice();
    }

    public static ScreenSettings getInstance() {
        if (ScreenSINGLETON == null) {
            synchronized(ScreenSettings.class) {
                if (ScreenSINGLETON == null) {
                   ScreenSINGLETON = new ScreenSettings();
                }
            }    
        }
        return ScreenSINGLETON;
    }

    public void setDevice() {
    System.out.println("Bienvenido a las desventuras del Enzito!");
      
      do {
         try {
            // Aquí podrías implementar una lógica para seleccionar la pantalla
            // Por ejemplo, podrías listar las pantallas disponibles y permitir al usuario elegir
            // En este caso, si el usuario solo cuenta con una pantalla, no será necesario seleccionar una pantalla específica.
            
            if (GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length == 1) {
               // Elige la única pantalla disponible
               device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
               screenSelection = true;
               }
            else{
               // Si hay más de una pantalla, solicita al usuario que elija una
               System.out.println("Ingrese en cual pantalla desea jugar: (1 a "+ GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length +")");
               int n = scanner.nextInt();
               device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[n-1]; // Selecciona la primera pantalla disponible
            } 
            screenSelection = true; // Simulamos que se ha seleccionado una pantalla
         } catch (Exception e) {
             System.out.println("Error al seleccionar la pantalla. Intente nuevamente.");
            scanner.nextLine(); // Limpiar entrada incorrecta
         }
      } while (!screenSelection);
      scanner.close();
        
    }

    public GraphicsDevice getDevice() {
        if (device == null) {
            setDevice(); // Asegurarse de que el dispositivo esté configurado
        }
        return device;
    }

    public int getScreenWidth() {
        return device.getDisplayMode().getWidth();
    }

    public int getScreenHeight() {
        return device.getDisplayMode().getHeight();
    }
    
    public double transformX() {
        GraphicsConfiguration gc = device.getDefaultConfiguration();
        AffineTransform transform = gc.getDefaultTransform()   ;
        double scaleX = transform.getScaleX();
        
        return scaleX;
    }

    public double transformY() {
        GraphicsConfiguration gc = device.getDefaultConfiguration();
        AffineTransform transform = gc.getDefaultTransform();
        double scaleY = transform.getScaleY();

        return scaleY;
    }

    public Dimension getScreenSize() {
        return new Dimension(getScreenWidth(), getScreenHeight());
    }


    public int getScreenCols() {
        int cols = (int) (getScreenWidth()/transformX()) / (Constants.TILE_SIZE.getSize()*Constants.SCALE.getSize());
        return cols; // Restamos 1 para evitar que se salga del borde derecho
    }

    public int getScreenRows() {
        int rows = (int) (getScreenHeight()/transformY()) / (Constants.TILE_SIZE.getSize()*Constants.SCALE.getSize());
        return rows;
    }

    public int getScaledWidth(ScreenSettings settings) {
        int width = (int) (settings.getScreenWidth() / settings.transformX());
        return width;
    }

    public int getScaledHeight(ScreenSettings settings) {
        int height = (int) (settings.getScreenHeight() / settings.transformY());
        return height;
    }


}