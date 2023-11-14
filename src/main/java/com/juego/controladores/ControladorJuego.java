package main.java.com.juego.controladores;

import main.java.com.juego.vistas.VistaTablero;
import java.util.List;

public class ControladorJuego {

    private VistaTablero vistaTablero;

    public ControladorJuego() {
        // Crea la vista del tablero al construir el controlador
        vistaTablero = new VistaTablero();
    }

    public void iniciarJuegoConJugadores(List<String> nombresJugadores) {
        // Lógica para iniciar el juego con los nombres de los jugadores
        System.out.println("Los jugadores son: " + nombresJugadores);

        // Aquí podrías, por ejemplo, crear las instancias de los jugadores, iniciar la lógica del juego, etc.

        // Actualiza la tabla de jugadores en la vista con los nombres y puntos iniciales
        String[] nombresArray = nombresJugadores.toArray(new String[0]);
        int[] puntos = new int[nombresJugadores.size()]; // asumiendo puntos iniciales como 0
        vistaTablero.actualizarJugadores(nombresArray, puntos);

        // Mostrar el tablero
        vistaTablero.mostrar();
    }
}
