/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serpientesYEscaleras.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaNombresJugadores extends JFrame {

    private JLabel tituloLabel;
    private JLabel mensajeLabel;
    private JTextField nombreField;
    private JButton continuarButton;
    private int numJugadores; // Número total de jugadores
    private String[] nombresJugadores; // Arreglo para almacenar los nombres de los jugadores

    // Constructor que recibe el número total de jugadores
    public VentanaNombresJugadores(int numJugadores) {
        super("Insertar Nombres de Jugadores");

        this.numJugadores = numJugadores;
        this.nombresJugadores = new String[numJugadores - 1]; // Corregido

        // Configuración del JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 200); // Ajustamos el tamaño de la ventana
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro

        // Crear componentes de la ventana
        tituloLabel = new JLabel("Inserta el nombre del jugador 1", SwingConstants.CENTER);
        mensajeLabel = new JLabel("", SwingConstants.CENTER);
        nombreField = new JTextField(20);
        continuarButton = new JButton("Continuar");

        // Configurar el layout del JFrame
        setLayout(new BorderLayout());
        JPanel panelCentral = new JPanel(new GridLayout(4, 1, 10, 10)); // GridLayout de 4 filas
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen
        panelCentral.setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro
        panelCentral.add(tituloLabel);
        panelCentral.add(mensajeLabel);
        panelCentral.add(nombreField);
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro
        panelBoton.add(continuarButton);
        panelCentral.add(panelBoton);

        add(panelCentral, BorderLayout.CENTER);

        // Agregar ActionListener al botón de continuar
        continuarButton.addActionListener(new ActionListener) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Guardar el nombre del jugador actual en el arreglo
                nombresJugadores[numJugadores - 2] = nombreField.getText();
            }
        }
           
            
    }
}
