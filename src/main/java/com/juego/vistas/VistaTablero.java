package main.java.com.juego.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;  // Importa la interfaz KeyListener
import main.java.com.juego.modelos.Tablero;


public class VistaTablero extends JFrame {

    private final Tablero tablero; // Instancia de la clase Tablero que maneja el modelo de datos
    private JButton btnEmpezarTurno; // Botón para iniciar el turno

    public VistaTablero() {
        setTitle("Juego Tiro al Blanco");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout(10, 10));

        // Inicializa la clase Tablero que contiene el modelo de la tabla de jugadores
        tablero = new Tablero();

        // Panel de jugadores con bordes y títulos
        JPanel panelJugadores = new JPanel();
        panelJugadores.setLayout(new BorderLayout());
        panelJugadores.setPreferredSize(new Dimension(200, 200));
        panelJugadores.setBorder(BorderFactory.createTitledBorder("Jugadores"));

        // Utiliza el modelo de la tabla del objeto tablero
        JTable tablaJugadores = new JTable(tablero.getModeloTabla());
        tablaJugadores.setPreferredScrollableViewportSize(new Dimension(180, 150));
        tablaJugadores.setFillsViewportHeight(true);
        tablaJugadores.setRowHeight(30);

        // Elimina la barra de desplazamiento vertical si no es necesaria
        JScrollPane scrollPane = new JScrollPane(tablaJugadores);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelJugadores.add(scrollPane, BorderLayout.CENTER);

        // Botón para empezar el turno
        btnEmpezarTurno = new JButton("Empezar Turno");
        panelJugadores.add(btnEmpezarTurno, BorderLayout.SOUTH);

        // Panel de tiro al blanco con bordes y títulos
        JPanel panelTiroAlBlanco = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Define the margin from the left and the bottom
                int margin = 10;

                // Sizes for each square
                int blackSize = 350;
                int blueSize = 260;
                int redSize = 180;
                int yellowSize = 80;

                // Bottom-left corner coordinates for the black square
                int blackX = margin;
                int blackY = getHeight() - blackSize - margin;

                // Draw the black square
                g.setColor(Color.BLACK);
                g.fillRect(blackX, blackY, blackSize, blackSize);

                // Bottom-left corner coordinates for the blue square
                int blueX = blackX + margin;
                int blueY = blackY - margin + (blackSize - blueSize);

                // Draw the blue square
                g.setColor(Color.BLUE);
                g.fillRect(blueX, blueY, blueSize, blueSize);

                // Bottom-left corner coordinates for the red square
                int redX = blueX + margin;
                int redY = blueY - margin + (blueSize - redSize);

                // Draw the red square
                g.setColor(Color.RED);
                g.fillRect(redX, redY, redSize, redSize);

                // Bottom-left corner coordinates for the yellow square
                int yellowX = redX + margin;
                int yellowY = redY - margin + (redSize - yellowSize);

                // Draw the yellow square
                g.setColor(Color.YELLOW);
                g.fillRect(yellowX, yellowY, yellowSize, yellowSize);
            }
        };
        panelTiroAlBlanco.setBorder(BorderFactory.createTitledBorder("Tiro al Blanco"));
        panelTiroAlBlanco.setPreferredSize(new Dimension(300, 300));

        // Agregar los paneles al frame
        add(panelJugadores, BorderLayout.WEST);
        add(panelTiroAlBlanco, BorderLayout.CENTER);

        // Preparar el frame para la visualización
        setSize(700, 450);
        setLocationRelativeTo(null);
    }

    // Método auxiliar para crear un panel de tecla
    private JPanel createKeyPanel(String keyText, Font font) {
        JLabel label = new JLabel(keyText);
        label.setFont(font);
        JPanel keyPanel = new JPanel();
        keyPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        keyPanel.add(label);
        return keyPanel;
    }

    // Método para actualizar la información de los jugadores en la tabla
    public void actualizarJugadores(String[] nombres, int[] puntos) {
        tablero.actualizarJugadores(nombres, puntos); // Delega la actualización al modelo de Tablero
    }

    public JButton getBtnEmpezarTurno() {
        return btnEmpezarTurno;
    }

    public void addKeyListenerToFrame(KeyListener listener) {
        addKeyListener(listener);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void mostrar() {
        setVisible(true);
    }

}
