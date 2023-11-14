package main.java.com.juego.controladores;

import main.java.com.juego.vistas.VistaTablero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import main.java.com.juego.controladores.ControladorTablero;

public class ControladorJuego {

    private VistaTablero vistaTablero;
    private ControladorTablero controladorTablero;

    public ControladorJuego() {
        // Crea la vista del tablero al construir el controlador
        vistaTablero = new VistaTablero();
    }

    public void iniciarJuegoConJugadores(List<String> nombresJugadores) {
        // Lógica para iniciar el juego con los nombres de los jugadores
        System.out.println("Los jugadores son: " + nombresJugadores);

        // Actualiza la tabla de jugadores en la vista con los nombres y puntos iniciales
        String[] nombresArray = nombresJugadores.toArray(new String[0]);
        int[] puntos = new int[nombresJugadores.size()]; // asumiendo puntos iniciales como 0
        vistaTablero.actualizarJugadores(nombresArray, puntos);

        // Mostrar el tablero
        vistaTablero.mostrar();

        // Crear una instancia de ControladorTablero y pasarle la vista y los nombres de los jugadores
        controladorTablero = new ControladorTablero(vistaTablero, nombresArray);

        // Añadir ActionListener al botón de empezar turno
        vistaTablero.getBtnEmpezarTurno().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorTablero.empezarTurno();
            }
        });
    }
}
