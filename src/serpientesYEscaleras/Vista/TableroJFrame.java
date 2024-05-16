package serpientesYEscaleras.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import serpientesYEscaleras.Modelo.Escalera;
import serpientesYEscaleras.Modelo.Serpiente;
import serpientesYEscaleras.Modelo.Tablero;

public class TableroJFrame extends JFrame {

    private JPanel tableroPanel;
    private JButton dadoButton;
    private JButton historialButton;
    private JTextArea historialTextArea;
    private JScrollPane historialScrollPane;
    private Tablero tablero;
    private JLabel[][] casillas;
    private JLabel[] jugadoresLabels;
    private ImageIcon[] fichasJugadores;
    private boolean juegoTerminado;

    public TableroJFrame(int filas, int columnas, Tablero tablero) {
        super("Tablero");

        this.tablero = tablero;
        this.casillas = new JLabel[filas][columnas];
        this.jugadoresLabels = new JLabel[tablero.getJugadores().length];
        this.fichasJugadores = new ImageIcon[tablero.getJugadores().length];
        this.juegoTerminado = false;
        tablero.rellenarTablero();

        // Configuración del JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Crear el panel del tablero
        tableroPanel = new JPanel(new GridLayout(filas, columnas));
        tableroPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tableroPanel.setBackground(new Color(220, 220, 220));

        // Crear las casillas del tablero con números o imágenes
        for (int fila = filas - 1; fila >= 0; fila--) {
            for (int columna = 0; columna < columnas; columna++) {
                int numeroCasilla;
                if (fila % 2 == 0) {
                    numeroCasilla = (fila * columnas) + columna + 1;
                } else {
                    numeroCasilla = ((fila + 1) * columnas) - columna;
                }

                JLabel casilla = new JLabel("", SwingConstants.CENTER);
                casilla.setPreferredSize(new Dimension(50, 50));
                casilla.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                if (fila % 2 == 0) {
                    casilla.setBackground(new Color(173, 216, 230));
                } else {
                    casilla.setBackground(new Color(255, 193, 128));
                }

                Object objeto = tablero.getJuego()[numeroCasilla - 1];
                if (objeto instanceof Serpiente) {
                    casilla.setIcon((ImageIcon) ((Serpiente) objeto).getIcon());
                    casilla.setOpaque(true); // Asegurarse de que la imagen no sea opacada
                } else if (objeto instanceof Escalera) {
                    casilla.setIcon((ImageIcon) ((Escalera) objeto).getIcon());
                    casilla.setOpaque(true);
                } else {
                    casilla.setText(String.valueOf(numeroCasilla));
                    casilla.setOpaque(true);
                }

                casillas[fila][columna] = casilla;
                tableroPanel.add(casilla);
            }
        }

        // Crear el botón para la imagen del dado
        ImageIcon dadoIcon = escalarImagen(new ImageIcon("C:\\Users\\monok\\Documents\\NetBeansProjects\\Serpientes y escaleras\\src\\Imagenes\\Dice.png"));
        dadoButton = new JButton(dadoIcon);

        dadoButton.setPreferredSize(new Dimension(dadoIcon.getIconWidth(), dadoIcon.getIconHeight()));
        dadoButton.setBorder(BorderFactory.createEmptyBorder());
        dadoButton.setContentAreaFilled(false);

        dadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarMovimientoJugador();
            }
        });

        // Crear el botón para mostrar el historial
        historialButton = new JButton("Mostrar Historial");
        historialButton.setEnabled(false); // Se habilita solo cuando el juego termina

        historialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historialTextArea.setText(tablero.obtenerHistorialJuego());
            }
        });

        historialTextArea = new JTextArea(10, 30);
        historialTextArea.setEditable(false);
        historialScrollPane = new JScrollPane(historialTextArea);

        setLayout(new BorderLayout());
        add(tableroPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(dadoButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre botones
        buttonPanel.add(historialButton);
        buttonPanel.add(Box.createVerticalGlue());

        add(buttonPanel, BorderLayout.EAST);
        add(historialScrollPane, BorderLayout.SOUTH); // Agregar el área de texto con el historial en la parte inferior

        // Inicializar las fichas de los jugadores
        for (int i = 0; i < tablero.getJugadores().length; i++) {
            fichasJugadores[i] = escalarImagen(new ImageIcon("C:\\Users\\monok\\Documents\\NetBeansProjects\\Serpientes y escaleras\\src\\Imagenes\\ficha.png")); // Ajusta la ruta
            jugadoresLabels[i] = new JLabel(fichasJugadores[i]);
            jugadoresLabels[i].setPreferredSize(new Dimension(50, 50));
            casillas[0][0].add(jugadoresLabels[i]); // Poner las fichas en la casilla inicial
            casillas[0][0].revalidate();
            casillas[0][0].repaint();
        }
    }

    private ImageIcon escalarImagen(ImageIcon icon) {
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    private void realizarMovimientoJugador() {
        if (juegoTerminado) {
            return;
        }
        int jugadorActual = tablero.getTurnoActual();
        int lanzamiento = tablero.realizarLanzamiento();
        System.out.println("Turno del jugador: " + tablero.getJugadores()[jugadorActual].getNombre());
        System.out.println("Lanzamiento del dado: " + lanzamiento);
        moverFichaJugador(jugadorActual, lanzamiento);
    }

    private void moverFichaJugador(int jugador, int lanzamiento) {
        int posicionActual = tablero.getJugadores()[jugador].getPosicionActual();
        System.out.println("Posición actual del jugador: " + posicionActual);
        tablero.moverJugador(lanzamiento, tablero.getJugadores()[jugador]);
        int nuevaPosicion = tablero.getJugadores()[jugador].getPosicionActual();
        System.out.println("Nueva posición del jugador: " + nuevaPosicion);

        // Actualizar la posición de la ficha en la interfaz gráfica
        actualizarFichaEnInterfaz(jugador, posicionActual, nuevaPosicion);

        // Verificar si el jugador ha ganado
        if (tablero.getJugadores()[jugador].isGanador()) {
            System.out.println("¡El jugador " + tablero.getJugadores()[jugador].getNombre() + " ha ganado el juego!");
            mostrarMensajeGanador(tablero.getJugadores()[jugador].getNombre(), lanzamiento, posicionActual, nuevaPosicion);
            juegoTerminado = true;
            dadoButton.setEnabled(false);
            historialButton.setEnabled(true); // Habilitar el botón de historial al terminar el juego
        } else {
            tablero.siguienteTurno(); // Mover al siguiente turno sin mover al jugador
        }
    }

    private void actualizarFichaEnInterfaz(int jugador, int posicionAnterior, int nuevaPosicion) {
        int filaAnterior = (posicionAnterior - 1) / 10;
        int columnaAnterior = (posicionAnterior - 1) % 10;

        if (posicionAnterior > 0) {
            casillas[filaAnterior][columnaAnterior].remove(jugadoresLabels[jugador]);
            casillas[filaAnterior][columnaAnterior].revalidate();
            casillas[filaAnterior][columnaAnterior].repaint();
        } else {
            casillas[0][0].remove(jugadoresLabels[jugador]);
            casillas[0][0].revalidate();
            casillas[0][0].repaint();
        }

        int filaNueva = (nuevaPosicion - 1) / 10;
        int columnaNueva = (nuevaPosicion - 1) % 10;

        casillas[filaNueva][columnaNueva].add(jugadoresLabels[jugador]);
        casillas[filaNueva][columnaNueva].revalidate();
        casillas[filaNueva][columnaNueva].repaint();
    }

    private void mostrarMensajeGanador(String nombreJugador, int lanzamiento, int posicionAnterior, int nuevaPosicion) {
        String mensaje = "¡El jugador " + nombreJugador + " ha ganado el juego!\n\n"
                + "Lanzamiento del dado: " + lanzamiento + "\n"
                + "Posición anterior: " + posicionAnterior + "\n"
                + "Nueva posición: " + nuevaPosicion;
        JOptionPane.showMessageDialog(this, mensaje, "Juego terminado", JOptionPane.INFORMATION_MESSAGE);
    }
}
