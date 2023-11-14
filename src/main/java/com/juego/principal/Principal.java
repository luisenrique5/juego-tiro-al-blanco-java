package main.java.com.juego.principal;

import main.java.com.juego.controladores.ControladorJuego;
import main.java.com.juego.vistas.vistaJuego; // Asegúrate de que esta clase exista

import javax.swing.*;

public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControladorJuego controlador = new ControladorJuego();
            vistaJuego vista = new vistaJuego(controlador); // Asegúrate de que esta clase esté correctamente definida
            vista.mostrar(); // Este método debe existir en tu clase VistaJuego
        });
    }
}
