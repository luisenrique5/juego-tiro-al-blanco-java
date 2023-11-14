package main.java.com.juego.modelos;

import javax.swing.table.DefaultTableModel;

public class Tablero {

    private final DefaultTableModel modeloTabla;
    private static final int MAX_JUGADORES = 5;

    public Tablero() {

        String[] columnas = {"Nombres", "Puntos"};

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        for (int i = 0; i < MAX_JUGADORES; i++) {
            modeloTabla.addRow(new Object[]{"", ""});
        }
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }


    public void actualizarJugadores(String[] nombres, int[] puntos) {
        for (int i = 0; i < MAX_JUGADORES; i++) {
            if (i < nombres.length) {
                modeloTabla.setValueAt(nombres[i], i, 0);
                modeloTabla.setValueAt(puntos[i], i, 1);
            } else {
                modeloTabla.setValueAt("", i, 0);
                modeloTabla.setValueAt("", i, 1);
            }
        }
    }
}
