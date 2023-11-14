package main.java.com.juego.vistas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import main.java.com.juego.controladores.ControladorJuego;

public class vistaJuego extends JFrame {
    private List<JTextField> jugadorTextFields;
    private JPanel panelPrincipal;
    private JButton anadirUsuarioButton;
    private JButton empezarJuegoButton;
    private static final int MAX_JUGADORES = 5;
    private ControladorJuego controladorJuego;

    public vistaJuego(ControladorJuego controlador) {
        super("Juego Tiro al Blanco");
        this.controladorJuego = controlador;
        jugadorTextFields = new ArrayList<>();
        iniciarComponentes();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 300);
    }

    private void iniciarComponentes() {
        Font fuenteTexto = new Font("Arial", Font.BOLD, 16);
        Color colorFondo = new Color(0xFFFFFF);
        Color colorBotonAnadir = new Color(0x3498DB);
        Color colorBotonEmpezar = new Color(0x2ECC71);
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

        empezarJuegoButton.addActionListener(e -> validarCamposYEmpezarJuego());
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

    private void validarCamposYEmpezarJuego() {
        List<String> nombresJugadores = new ArrayList<>();
        for (JTextField jugadorTextField : jugadorTextFields) {
            String nombre = jugadorTextField.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            nombresJugadores.add(nombre);
        }

        controladorJuego.iniciarJuegoConJugadores(nombresJugadores);
    }


    public void mostrar() {
        setVisible(true);
    }
}
