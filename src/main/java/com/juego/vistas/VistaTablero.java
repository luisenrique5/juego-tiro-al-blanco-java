package main.java.com.juego.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

import main.java.com.juego.modelos.Tablero;

public class VistaTablero extends JFrame {

    private final Tablero tablero;
    private JButton btnEmpezarTurno;
    private int estrella1X = 10;
    private int estrella1Y = 10;
    private int estrella2X = 30;
    private int estrella2Y = 10;

    public VistaTablero() {
        setTitle("Juego Tiro al Blanco");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout(10, 10));


        tablero = new Tablero();


        JPanel panelJugadores = new JPanel();
        panelJugadores.setLayout(new BorderLayout());
        panelJugadores.setPreferredSize(new Dimension(200, 200));
        panelJugadores.setBorder(BorderFactory.createTitledBorder("Jugadores"));


        JTable tablaJugadores = new JTable(tablero.getModeloTabla());
        tablaJugadores.setPreferredScrollableViewportSize(new Dimension(180, 150));
        tablaJugadores.setFillsViewportHeight(true);
        tablaJugadores.setRowHeight(30);


        JScrollPane scrollPane = new JScrollPane(tablaJugadores);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelJugadores.add(scrollPane, BorderLayout.CENTER);


        btnEmpezarTurno = new JButton("Empezar Turno");
        panelJugadores.add(btnEmpezarTurno, BorderLayout.SOUTH);


        JPanel panelTiroAlBlanco = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);


                int margin = 10;


                int blackSize = 350;
                int blueSize = 260;
                int redSize = 180;
                int yellowSize = 80;


                int blackX = margin;
                int blackY = getHeight() - blackSize - margin;


                g.setColor(Color.BLACK);
                g.fillRect(blackX, blackY, blackSize, blackSize);


                int blueX = blackX + margin;
                int blueY = blackY - margin + (blackSize - blueSize);


                g.setColor(Color.BLUE);
                g.fillRect(blueX, blueY, blueSize, blueSize);


                int redX = blueX + margin;
                int redY = blueY - margin + (blueSize - redSize);


                g.setColor(Color.RED);
                g.fillRect(redX, redY, redSize, redSize);


                int yellowX = redX + margin;
                int yellowY = redY - margin + (redSize - yellowSize);


                g.setColor(Color.YELLOW);
                g.fillRect(yellowX, yellowY, yellowSize, yellowSize);

                g.setColor(Color.GREEN);
                dibujarEstrella(g, estrella1X, estrella1Y);
                dibujarEstrella(g, estrella2X + 20, estrella2Y);
            }
        };
        panelTiroAlBlanco.setBorder(BorderFactory.createTitledBorder("Tiro al Blanco"));
        panelTiroAlBlanco.setPreferredSize(new Dimension(300, 300));

        add(panelJugadores, BorderLayout.WEST);
        add(panelTiroAlBlanco, BorderLayout.CENTER);

        setSize(700, 450);
        setLocationRelativeTo(null);
    }


    private JPanel createKeyPanel(String keyText, Font font) {
        JLabel label = new JLabel(keyText);
        label.setFont(font);
        JPanel keyPanel = new JPanel();
        keyPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        keyPanel.add(label);
        return keyPanel;
    }

    public void actualizarJugadores(String[] nombres, int[] puntos) {
        tablero.actualizarJugadores(nombres, puntos);
    }

    public JButton getBtnEmpezarTurno() {
        return btnEmpezarTurno;
    }

    public void addKeyListenerToFrame(KeyListener listener) {
        addKeyListener(listener);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    private void dibujarEstrella(Graphics g, int x, int y) {
        int[] xPoints = {x, x + 5, x + 10, x + 6, x + 8, x, x - 8, x - 6, x - 10, x - 5};
        int[] yPoints = {y, y + 5, y + 5, y + 8, y + 15, y + 10, y + 15, y + 8, y + 5, y + 5};
        g.fillPolygon(xPoints, yPoints, 10);
    }

    public void actualizarPosicionEstrella(int puntuacion, int intento) {
        int targetX = 0;
        int targetY = 0;
        if (puntuacion >= 0 && puntuacion <= 44) {
            targetX = 300;
            targetY = 300;
        } else if (puntuacion >= 45 && puntuacion <= 54) {
            targetX = 100;
            targetY = 150;
        } else if (puntuacion >= 55 && puntuacion <= 69) {
            targetX = 100;
            targetY = 250;
        } else if (puntuacion >= 70 && puntuacion <= 80) {
            targetX = 80;
            targetY = 350;
        }


        if (intento == 0) {
            estrella1X = targetX;
            estrella1Y = targetY;
        } else if (intento == 1) {
            estrella2X = targetX;
            estrella2Y = targetY;
        }

        repaint();
    }

    public void reiniciarPosicionEstrellas() {
        estrella1X = 10;
        estrella1Y = 10;
        estrella2X = 30;
        estrella2Y = 10;
        repaint();
    }

    public void mostrar() {
        setVisible(true);
    }

}
