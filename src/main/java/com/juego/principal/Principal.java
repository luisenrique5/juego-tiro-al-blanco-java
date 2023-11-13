package main.java.com.juego.principal;

import main.java.com.juego.vistas.vistaJuego;

import javax.swing.*;

public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            vistaJuego vista = new vistaJuego();
            vista.mostrar();
        });
    }
}
