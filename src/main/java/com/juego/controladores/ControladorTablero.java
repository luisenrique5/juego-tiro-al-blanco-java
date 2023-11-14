package main.java.com.juego.controladores;

import main.java.com.juego.vistas.VistaTablero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ControladorTablero {
    private VistaTablero vistaTablero;
    private String[] nombresJugadores;
    private int[] puntuaciones;
    private int conteoTeclas = 0;
    private int turnoActual = 0;
    private int intentoActual = 0;
    private Timer temporizador;

    public ControladorTablero(VistaTablero vista, String[] nombresJugadores) {
        this.vistaTablero = vista;
        this.nombresJugadores = nombresJugadores;
        this.puntuaciones = new int[nombresJugadores.length];

        this.vistaTablero.addKeyListenerToFrame(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                manejarPresionTecla(e);
            }
        });

        this.vistaTablero.setFocusable(true);
        this.vistaTablero.requestFocusInWindow();
    }

    private void manejarPresionTecla(KeyEvent e) {
        if ((e.getKeyChar() == 't' || e.getKeyChar() == 'h') && temporizador.isRunning()) {
            conteoTeclas++;
        }
    }

    public void empezarTurno() {
        // Restablecer el conteo de teclas y puntuaciones para el nuevo turno
        conteoTeclas = 0;
        if (turnoActual == 0 && intentoActual == 0) {
            for (int i = 0; i < puntuaciones.length; i++) {
                puntuaciones[i] = 0;
            }
        }

        JOptionPane.showMessageDialog(vistaTablero, "Es tu turno, " + nombresJugadores[turnoActual] +
                ". Intento " + (intentoActual + 1) +
                ". Presiona T y H lo más rápido que puedas para darle en el blanco.");

        vistaTablero.requestFocusInWindow();

        temporizador = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarTurno();
            }
        });
        temporizador.setRepeats(false);
        temporizador.start();
    }


    private void finalizarTurno() {
        temporizador.stop();
        JOptionPane.showMessageDialog(vistaTablero, "Tu puntuación fue: " + conteoTeclas);
        puntuaciones[turnoActual] += conteoTeclas;

        vistaTablero.actualizarJugadores(nombresJugadores, puntuaciones);

        intentoActual++;
        if (intentoActual >= 2) {
            intentoActual = 0;
            turnoActual = (turnoActual + 1) % nombresJugadores.length;
        }

        if (turnoActual == 0 && intentoActual == 0) {
            determinarGanador();
        } else {
            empezarTurno(); // Iniciar el siguiente turno automáticamente
        }
    }

    private void determinarGanador() {
        int maxPuntuacion = 0;
        int jugadorGanador = 0;
        for (int i = 0; i < puntuaciones.length; i++) {
            if (puntuaciones[i] > maxPuntuacion) {
                maxPuntuacion = puntuaciones[i];
                jugadorGanador = i;
            }
        }
        JOptionPane.showMessageDialog(vistaTablero, "El ganador es " + nombresJugadores[jugadorGanador] + " con " + maxPuntuacion + " puntos.");
        // Opcional: Aquí puedes agregar una opción para reiniciar el juego o cerrarlo.
    }

    public void iniciarJuego() {
        empezarTurno();
    }
}
