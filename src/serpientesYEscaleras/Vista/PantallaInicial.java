package serpientesYEscaleras.Vista;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USUARIO
 */
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//
//public class PantallaInicial extends JFrame {
//    private JLabel bienvenidaLabel;
//    private JLabel instruccionesLabel;
//    private JLabel jugadoresLabel;
//    private JTextField jugadoresField;
//    private JLabel casillasLabel;
//    private JTextField casillasField;
//    private JButton empezarButton;
//
//    public PantallaInicial() {
//        super("Inicio del Juego");
//
//        // Configuración del JFrame
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(500, 250); // Ajustamos el tamaño de la ventana
//        setLocationRelativeTo(null);
//        getContentPane().setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro
//
//        // Crear componentes de la pantalla inicial
//        bienvenidaLabel = new JLabel("¡Bienvenido a Escaleras y Serpientes!", SwingConstants.CENTER);
//        bienvenidaLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Ajustamos el tamaño de la fuente
//        instruccionesLabel = new JLabel("Configura tu juego", SwingConstants.CENTER);
//        jugadoresLabel = new JLabel("Número de jugadores:");
//        jugadoresField = new JTextField(10);
//        casillasLabel = new JLabel("Número de casillas:");
//        casillasField = new JTextField(10);
//        empezarButton = new JButton("Empezar");
//        empezarButton.setBackground(new Color(153, 102, 255)); // Color morado suave para el botón
//        empezarButton.setForeground(Color.WHITE); // Texto blanco en el botón
//
//        // Configurar el layout del JFrame
//        setLayout(new BorderLayout());
//        JPanel panelCentral = new JPanel(new GridLayout(6, 1, 10, 10)); // Cambiamos a un GridLayout de 6 filas
//        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen
//        panelCentral.setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro
//        panelCentral.add(bienvenidaLabel);
//        panelCentral.add(instruccionesLabel);
//        JPanel panelJugadores = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        panelJugadores.setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro
//        panelJugadores.add(jugadoresLabel);
//        panelJugadores.add(jugadoresField);
//        panelCentral.add(panelJugadores);
//        JPanel panelCasillas = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        panelCasillas.setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro
//        panelCasillas.add(casillasLabel);
//        panelCasillas.add(casillasField);
//        panelCentral.add(panelCasillas);
//        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        panelBoton.setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro
//        panelBoton.add(empezarButton);
//        panelCentral.add(panelBoton);
//
//        add(panelCentral, BorderLayout.CENTER);
//
//        // Agregar ActionListener al botón de empezar
//        empezarButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int numJugadores = Integer.parseInt(jugadoresField.getText());
//                int numCasillas = Integer.parseInt(casillasField.getText());
//                // Crear y mostrar la ventana del tablero
//                TableroJFrame tablero = new TableroJFrame(numCasillas, numCasillas);
//                tablero.setVisible(true);
//            }
//        });
//    }
//
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaInicial extends JFrame {
    private JLabel bienvenidaLabel;
    private JLabel instruccionesLabel;
    private JLabel jugadoresLabel;
    private JTextField jugadoresField;
    private JLabel casillasLabel;
    private JTextField casillasField;
    private JButton continuarButton; // Cambio de "Empezar" a "Continuar"

    public PantallaInicial() {
        super("Inicio del Juego");

        // Configuración del JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 320); // Ajustamos el tamaño de la ventana
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro

        // Crear componentes de la pantalla inicial
        bienvenidaLabel = new JLabel("¡Bienvenido a Escaleras y Serpientes!", SwingConstants.CENTER);
        bienvenidaLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Ajustamos el tamaño de la fuente
        instruccionesLabel = new JLabel("Configura tu juego", SwingConstants.CENTER);
        jugadoresLabel = new JLabel("Número de jugadores:");
        jugadoresField = new JTextField(10);
        casillasLabel = new JLabel("Número de casillas:");
        casillasField = new JTextField(10);
        continuarButton = new JButton("Continuar"); // Cambio de "Empezar" a "Continuar"
        continuarButton.setBackground(new Color(153, 102, 255)); // Color morado suave para el botón
        continuarButton.setForeground(Color.WHITE); // Texto blanco en el botón

        // Configurar el layout del JFrame
        setLayout(new BorderLayout());
        JPanel panelCentral = new JPanel(new GridLayout(6, 1, 10, 10)); // Cambiamos a un GridLayout de 6 filas
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen
        panelCentral.setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro
        panelCentral.add(bienvenidaLabel);
        panelCentral.add(instruccionesLabel);
        JPanel panelJugadores = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelJugadores.setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro
        panelJugadores.add(jugadoresLabel);
        panelJugadores.add(jugadoresField);
        panelCentral.add(panelJugadores);
        JPanel panelCasillas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCasillas.setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro
        panelCasillas.add(casillasLabel);
        panelCasillas.add(casillasField);
        panelCentral.add(panelCasillas);
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro
        panelBoton.add(continuarButton); // Cambio de "empezarButton" a "continuarButton"
        panelCentral.add(panelBoton);

        add(panelCentral, BorderLayout.CENTER);

        // Agregar ActionListener al botón de continuar
         continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numJugadores = Integer.parseInt(jugadoresField.getText());
                int numCasillas = Integer.parseInt(casillasField.getText());
                // Crear y mostrar la ventana del tablero
                TableroJFrame tablero = new TableroJFrame(numCasillas, numCasillas);
                tablero.setVisible(true);

                // Crear y mostrar la ventana para ingresar nombres de jugadores
                VentanaNombresJugadores nombresJugadores = new VentanaNombresJugadores(numJugadores);
                nombresJugadores.setVisible(true);
            }
        });
    }
}

