/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serpientesYEscaleras.Vista;

import javax.swing.*;
import java.awt.*;

public class TableroJFrame extends JFrame {
    private JPanel tableroPanel;

    public TableroJFrame(int filas, int columnas) {
        super("Tablero");

        // Configuración del JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        // Crear el panel del tablero
        tableroPanel = new JPanel(new GridLayout(filas, columnas));
        tableroPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tableroPanel.setBackground(new Color(220, 220, 220)); // Color suave y tenue

        // Crear las casillas del tablero con números
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                int numeroCasilla;
                if (fila % 2 == 0) { // Si la fila es par
                    numeroCasilla = (fila * columnas) + columna + 1;
                } else { // Si la fila es impar
                    numeroCasilla = ((fila + 1) * columnas) - columna;
                }
                JLabel casilla = new JLabel(String.valueOf(numeroCasilla), SwingConstants.CENTER);
                casilla.setPreferredSize(new Dimension(50, 50));
                casilla.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                casilla.setOpaque(true); // Hace que la casilla sea opaca para que se pueda aplicar el color de fondo
                // Color de fondo alternativo para filas pares e impares
                if (fila % 2 == 0) {
                    casilla.setBackground(new Color(173, 216, 230)); // Azul claro para filas pares
                } else {
                    casilla.setBackground(new Color(255, 193, 128)); // Naranja claro para filas impares
                }
                tableroPanel.add(casilla);
            }
        }

        // Configurar el layout del JFrame
        setLayout(new BorderLayout());
        add(tableroPanel, BorderLayout.CENTER);
    }
}


