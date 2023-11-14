package main.java.com.juego.modelos;

import javax.swing.table.DefaultTableModel;

public class Tablero {

    private final DefaultTableModel modeloTabla;
    private static final int MAX_JUGADORES = 5;

    public Tablero() {
        // Define las columnas de la tabla
        String[] columnas = {"Nombres", "Puntos"};
        // Crea un modelo de tabla que no permita editar las celdas directamente
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Deshabilita la edición directa de las celdas
            }
        };

        // Llena la tabla con filas vacías como espacios para los jugadores
        for (int i = 0; i < MAX_JUGADORES; i++) {
            modeloTabla.addRow(new Object[]{"", ""}); // Añade filas vacías
        }
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    // Método para actualizar la información de los jugadores en la tabla
    public void actualizarJugadores(String[] nombres, int[] puntos) {
        for (int i = 0; i < MAX_JUGADORES; i++) {
            if (i < nombres.length) {
                modeloTabla.setValueAt(nombres[i], i, 0); // Actualiza el nombre
                modeloTabla.setValueAt(puntos[i], i, 1); // Actualiza los puntos
            } else {
                modeloTabla.setValueAt("", i, 0); // Limpia el nombre
                modeloTabla.setValueAt("", i, 1); // Limpia los puntos
            }
        }
    }
}
