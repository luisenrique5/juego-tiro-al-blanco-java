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
        vistaTablero = new VistaTablero();
    }

    public void iniciarJuegoConJugadores(List<String> nombresJugadores) {

        String[] nombresArray = nombresJugadores.toArray(new String[0]);
        int[] puntos = new int[nombresJugadores.size()];
        vistaTablero.actualizarJugadores(nombresArray, puntos);


        vistaTablero.mostrar();

        controladorTablero = new ControladorTablero(vistaTablero, nombresArray);

        vistaTablero.getBtnEmpezarTurno().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorTablero.empezarTurno();
            }
        });
    }
}
