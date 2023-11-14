package main.java.com.juego.principal;

import main.java.com.juego.controladores.ControladorJuego;
import main.java.com.juego.vistas.vistaJuego;

import javax.swing.*;

public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControladorJuego controlador = new ControladorJuego();
            vistaJuego vista = new vistaJuego(controlador);
            vista.mostrar();
        });
    }
}
