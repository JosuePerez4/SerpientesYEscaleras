package serpientesYEscaleras.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import serpientesYEscaleras.Modelo.Tablero;

public class VentanaNombresJugadores extends JFrame {

    private JLabel tituloLabel;
    private JLabel mensajeLabel;
    private JTextField nombreField;
    private JButton continuarButton;
    private int numJugadores; // Número total de jugadores
    private String[] nombresJugadores; // Arreglo para almacenar los nombres de los jugadores
    private int jugadorActual; // Índice del jugador actual
    private Tablero t;

    // Constructor que recibe el número total de jugadores
    public VentanaNombresJugadores(int numJugadores, Tablero tablero) {
        super("Insertar Nombres de Jugadores");

        this.numJugadores = numJugadores;
        this.nombresJugadores = new String[numJugadores];
        this.jugadorActual = 0;
        this.numJugadores = 0;
        this.t = tablero; // Corrección del tamaño del arreglo

        // Configuración del JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 200); // Ajustamos el tamaño de la ventana
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro

        // Crear componentes de la ventana
        int actual = jugadorActual+1;
        tituloLabel = new JLabel("Inserta el nombre del jugador " + actual, SwingConstants.CENTER);
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
        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
// Guardar el nombre del jugador actual en el arreglo
                if (!nombreField.getText().isEmpty()) {
                    nombresJugadores[jugadorActual] = nombreField.getText();
                    t.agregarJugador(nombresJugadores[jugadorActual]);
                    tituloLabel.setText("Inserta el nombre del jugador " + (jugadorActual+2));
                    nombreField.setText(""); // Limpiar el campo de texto
                    nombreField.requestFocusInWindow(); // Colocar el puntero en el campo de texto
                    jugadorActual++;
                    t.imprimirJugadores();

                    if (jugadorActual < numJugadores) {
//                        // Actualizar el título para el próximo jugador
//                        int actual = jugadorActual + 1;
//                        tituloLabel.setText("Inserta el nombre del jugador " + actual);
//                        nombreField.setText(""); // Limpiar el campo de texto
//                        nombreField.requestFocusInWindow(); // Colocar el puntero en el campo de texto
                    } else {
                        // Todos los nombres han sido ingresados
                        JOptionPane.showMessageDialog(null, "Todos los nombres han sido ingresados.");
                        for (int i = 0; i < nombresJugadores.length; i++) {
                            System.out.println("Jugador " + (i + 1) + ": " + nombresJugadores[i]);
                        }
                        dispose(); // Cerrar la ventana de ingreso de nombres
                    }
                } else {
                    // Mostrar un mensaje de error si el campo está vacío
                    mensajeLabel.setText("El nombre no puede estar vacío.");
                }
            }
        });
    }
}
