/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package serpientesYEscaleras.Controlador;

import serpientesYEscaleras.Vista.PantallaInicial;
import javax.swing.SwingUtilities;

/**
 *
 * @author USUARIO
 */
public class Jueguito {

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new TableroJFrame(10, 10).setVisible(true);
//        });
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PantallaInicial().setVisible(true);
        });
    }
}
