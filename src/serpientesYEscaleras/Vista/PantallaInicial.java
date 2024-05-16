package serpientesYEscaleras.Vista;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author USUARIO
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import serpientesYEscaleras.Modelo.Tablero;

public class PantallaInicial extends JFrame {

    private JLabel bienvenidaLabel;
    private JLabel instruccionesLabel;
    private JLabel jugadoresLabel;
    private JTextField jugadoresField;
    private JLabel casillasLabel;
    private JTextField casillasField;
    private JTextField serpientesField;
    private JLabel serpientesLabel;
    private JLabel escalerasLabel;
    private JTextField escalerasField;
    private JButton agregarSerpienteButton;
    private JButton agregarEscaleraButton;
    private JButton continuarButton;

    private List<int[]> serpientes;
    private List<int[]> escaleras;

    public PantallaInicial() {
        super("Inicio del Juego");
        // Configuración del JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400); // Ajustamos el tamaño de la ventana
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro

        // Inicializar las listas
        serpientes = new ArrayList<>();
        escaleras = new ArrayList<>();

        // Crear componentes de la pantalla inicial
        bienvenidaLabel = new JLabel("¡Bienvenido a Escaleras y Serpientes!", SwingConstants.CENTER);
        bienvenidaLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Ajustamos el tamaño de la fuente
        instruccionesLabel = new JLabel("Configura tu juego", SwingConstants.CENTER);
        jugadoresLabel = new JLabel("Número de jugadores:");
        jugadoresField = new JTextField(10);
        casillasLabel = new JLabel("Número de casillas (si escribes 10, entonces 10*10): ");
        casillasField = new JTextField(10);
        serpientesLabel = new JLabel("Añade las coordenadas de la serpiente (ej. 12-5):");
        serpientesField = new JTextField(10);
        agregarSerpienteButton = new JButton("Agregar Serpiente");

        escalerasLabel = new JLabel("Añade las coordenadas de la escalera (ej. 3-14):");
        escalerasField = new JTextField(10);
        agregarEscaleraButton = new JButton("Agregar Escalera");
        continuarButton = new JButton("Continuar"); // Cambio de "Empezar" a "Continuar"
        continuarButton.setBackground(new Color(153, 102, 255)); // Color morado suave para el botón
        continuarButton.setForeground(Color.WHITE); // Texto blanco en el botón

        // Configurar el layout del JFrame
        setLayout(new BorderLayout());
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen
        panelCentral.setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro

        bienvenidaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(bienvenidaLabel);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre componentes

        instruccionesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(instruccionesLabel);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre componentes

        JPanel panelJugadores = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelJugadores.setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro
        panelJugadores.add(jugadoresLabel);
        panelJugadores.add(jugadoresField);
        panelCentral.add(panelJugadores);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre componentes

        JPanel panelCasillas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCasillas.setBackground(new Color(220, 255, 220)); // Color de fondo verdoso claro
        panelCasillas.add(casillasLabel);
        panelCasillas.add(casillasField);
        panelCentral.add(panelCasillas);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre componentes

        JPanel panelSerpientes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSerpientes.setBackground(new Color(220, 255, 220));
        panelSerpientes.add(serpientesLabel);
        panelSerpientes.add(serpientesField);
        panelSerpientes.add(agregarSerpienteButton);
        panelCentral.add(panelSerpientes);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre componentes

        JPanel panelEscaleras = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelEscaleras.setBackground(new Color(220, 255, 220));
        panelEscaleras.add(escalerasLabel);
        panelEscaleras.add(escalerasField);
        panelEscaleras.add(agregarEscaleraButton);
        panelCentral.add(panelEscaleras);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre componentes

        continuarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(continuarButton);

        add(panelCentral, BorderLayout.CENTER);

        // Acción del botón para agregar serpientes
        agregarSerpienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String coordenadas = serpientesField.getText();
                if (!coordenadas.isEmpty()) {
                    String[] pos = coordenadas.split("-");
                    if (pos.length == 2) {
                        int inicioPos = Integer.parseInt(pos[0].trim());
                        int finPos = Integer.parseInt(pos[1].trim());
                        serpientes.add(new int[]{inicioPos, finPos});
                        serpientesField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Formato incorrecto. Usa el formato inicio-fin (ej. 12-5).");
                    }
                }
            }
        });

        // Acción del botón para agregar escaleras
        agregarEscaleraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String coordenadas = escalerasField.getText();
                if (!coordenadas.isEmpty()) {
                    String[] pos = coordenadas.split("-");
                    if (pos.length == 2) {
                        int inicioPos = Integer.parseInt(pos[0].trim());
                        int finPos = Integer.parseInt(pos[1].trim());
                        escaleras.add(new int[]{inicioPos, finPos});
                        escalerasField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Formato incorrecto. Usa el formato inicio-fin (ej. 3-14).");
                    }
                }
            }
        });

        // Agregar ActionListener al botón de continuar
        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se capturan los datos
                int numJugadores = Integer.parseInt(jugadoresField.getText());
                int numCasillas = Integer.parseInt(casillasField.getText());
                Tablero t = new Tablero(numCasillas * numCasillas, numJugadores);

                // Agregar las serpientes al tablero
                for (int[] serpiente : serpientes) {
                    t.agregarSerpiente(serpiente[0], serpiente[1]);
                }

                // Agregar las escaleras al tablero
                for (int[] escalera : escaleras) {
                    t.agregarEscalera(escalera[0], escalera[1]);
                }

                t.rellenarTablero();
                t.imprimirTablero();

                // Crear y mostrar la ventana del tablero
                TableroJFrame tablero = new TableroJFrame(numCasillas, numCasillas, t);
                tablero.setVisible(true);

                // Crear y mostrar la ventana para ingresar nombres de jugadores
                VentanaNombresJugadores nombresJugadores = new VentanaNombresJugadores(numJugadores, t);
                nombresJugadores.setVisible(true);
                // Cerrar la ventana actual
                dispose();
            }
        });
    }
}
