package main.java.com.juego.vistas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class vistaJuego extends JFrame {
    private List<JTextField> jugadorTextFields;
    private JPanel panelPrincipal;
    private JButton anadirUsuarioButton;
    private JButton empezarJuegoButton;
    private static final int MAX_JUGADORES = 5;

    public vistaJuego() {
        super("Juego Tiro al Blanco");
        jugadorTextFields = new ArrayList<>();
        iniciarComponentes();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
    }

    private void iniciarComponentes() {
        Font fuenteTexto = new Font("Arial", Font.BOLD, 16);
        Color colorFondo = new Color(0xFFFFFF); // Blanco
        Color colorBotonAnadir = new Color(0x3498DB); // Azul
        Color colorBotonEmpezar = new Color(0x2ECC71); // Verde
        Color colorTextoBoton = Color.WHITE;
        Insets margenBotones = new Insets(10, 20, 10, 20);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPrincipal.setBackground(colorFondo);

        JLabel titulo = new JLabel("juego tiro al blanco");
        titulo.setFont(fuenteTexto);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelPrincipal.add(Box.createVerticalStrut(20));
        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createVerticalStrut(20));

        for (int i = 1; i <= 2; i++) {
            anadirCampoJugador("Jugador " + i);
        }

        JScrollPane scrollPane = new JScrollPane(panelPrincipal);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel panelDeBotones = crearPanelDeBotones();

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panelDeBotones, BorderLayout.SOUTH);

        setSize(600, 300);
    }

    private JPanel crearPanelDeBotones() {
        JPanel panelDeBotones = new JPanel();
        panelDeBotones.setLayout(new FlowLayout(FlowLayout.CENTER));

        anadirUsuarioButton = new JButton("añadir jugador");
        configurarBoton(anadirUsuarioButton, new Font("Arial", Font.BOLD, 16), new Color(0x3498DB), Color.WHITE, new Insets(10, 20, 10, 20));
        anadirUsuarioButton.addActionListener(e -> anadirNuevoCampoJugador());

        empezarJuegoButton = new JButton("jugar");
        configurarBoton(empezarJuegoButton, new Font("Arial", Font.BOLD, 16), new Color(0x2ECC71), Color.WHITE, new Insets(10, 20, 10, 20));

        panelDeBotones.add(anadirUsuarioButton);
        panelDeBotones.add(empezarJuegoButton);

        return panelDeBotones;
    }

    private void configurarBoton(JButton boton, Font fuente, Color colorFondo, Color colorTexto, Insets margen) {
        boton.setFont(fuente);
        boton.setBackground(colorFondo);
        boton.setForeground(colorTexto);
        boton.setMargin(margen);
    }

    private void anadirCampoJugador(String nombreJugador) {
        JLabel etiquetaJugador = new JLabel(nombreJugador);
        etiquetaJugador.setFont(new Font("Arial", Font.BOLD, 16));
        etiquetaJugador.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField campoJugador = new JTextField(20);
        campoJugador.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoJugador.getPreferredSize().height));
        jugadorTextFields.add(campoJugador);

        panelPrincipal.add(etiquetaJugador);
        panelPrincipal.add(campoJugador);
        panelPrincipal.add(Box.createVerticalStrut(10));

        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }

    private void anadirNuevoCampoJugador() {
        if (jugadorTextFields.size() < MAX_JUGADORES) {
            anadirCampoJugador("Jugador " + (jugadorTextFields.size() + 1));
        } else {
            anadirUsuarioButton.setEnabled(false);
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}
