package main.java.model.constants;

import java.util.Scanner;
import java.awt.*;

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

    public Dimension getScreenSize() {
        return new Dimension(getScreenWidth(), getScreenHeight());
    }


    public int getScreenCols() {
        int cols = (int) (getScreenWidth() / (Constants.TILE_SIZE.getSize()*Constants.SCALE.getSize()));
        return cols; // Restamos 1 para evitar que se salga del borde derecho
    }

    public int getScreenRows() {
        int rows = (int) (getScreenHeight() / (Constants.TILE_SIZE.getSize()*Constants.SCALE.getSize()));
        return rows;
    }


}