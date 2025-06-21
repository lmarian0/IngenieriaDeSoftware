# Ingeniería de Software - Juego Java
# Team: Java El Mate

Este proyecto es un juego desarrollado en Java como parte de la materia Ingeniería de Software.

## Estructura del Proyecto

- **src/main/java/**: Código fuente principal del juego.
  - **controller/**: Controladores de entrada y lógica de juego.
  - **model/**: Lógica de negocio, entidades del juego, constantes, fábrica de enemigos, estados, ítems, mapa, observer, power-ups.
  - **view/**: Interfaz gráfica, ventana principal, barras de vida/XP, recursos visuales, HUD.
- **src/test/java/**: Pruebas unitarias de los distintos módulos.
- **lib/**: Librerías externas (incluye JUnit para testing).

## Requisitos
- Java 8 o superior
- JUnit (incluido en `lib/`)

## Cómo ejecutar el juego
1. Compila el proyecto:
   - Navega a la carpeta raíz del proyecto.
   - Compila usando tu IDE favorito o desde consola:
     ```
     javac -cp lib/junit-platform-console-standalone-1.10.2.jar -d bin src/main/java/**/*.java
     ```
2. Ejecuta el juego:
   - Desde la carpeta raíz:
     ```
     java -cp bin main.java.Main
     ```

## Cómo correr los tests
1. Compila los tests:
   ```
   javac -cp lib/junit-platform-console-standalone-1.10.2.jar;bin -d bin src/test/java/**/*.java
   ```
2. Ejecuta los tests:
   ```
   java -jar lib/junit-platform-console-standalone-1.10.2.jar --class-path bin --scan-class-path
   ```
## Jugabilidad
-  W, A, S, D: Mover al personaje principal  (arriba, izquierda, abajo, derecha).
-  Espacio: Golpear/atacar.
-  K: Invocar aliado.
-  ESC: Salir del juego.

## Descripción general
-  El juego utiliza el patrón MVC (Modelo-Vista-Controlador).
-  Incluye manejo de estados de juego, HUD, control de jugador, enemigos y mapa.
-  El HUD muestra información relevante como vida y experiencia.
-  El sistema es fácilmente extensible para agregar nuevos personajes, enemigos o power-ups.
-  Si se desean revisar más texturas para aliados, o cambiar las mismas, se puede revisar la carpeta `src/main/java/view/resources/Aliades`. Los aliados se crean desde el módulo `controller`.

### Cómo agregar nuevos PowerUps
-  Los PowerUps se encuentran en `src/main/java/model/items/PowerUps/` y su lógica de creación en `src/main/java/model/PowerUpFactory/`.
-  Para agregar un nuevo PowerUp, crea una nueva clase en la carpeta de PowerUps y registra su creación en la fábrica correspondiente (`PowerUpFactory.java`).

### Cómo agregar nuevos enemigos
-  Los enemigos se encuentran en `src/main/java/model/Factory/` y sus clases específicas (por ejemplo, `Goblin`, `Tank`, etc.).
-  Para agregar un nuevo enemigo, crea una nueva clase en esa carpeta y registra su creación en la fábrica de enemigos (`EnemyFactory.java`).

## Créditos
-  Proyecto realizado por estudiantes de Ingeniería en Computación, 2025.

## Contacto
Para dudas o sugerencias, contactar a los autores del proyecto.
