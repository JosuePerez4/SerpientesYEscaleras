/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serpientesYEscaleras.Modelo;

/**
 *
 * @author monok
 */
public class Serpiente {
    private int cabeza;
    private int cola;

    public Serpiente (int cabeza, int cola) {
        this.cabeza = cabeza;
        this.cola = cola;
    }
    public int getCabeza() {
        return cabeza;
    }

    public void setCabeza(int cabeza) {
        this.cabeza = cabeza;
    }

    public int getCola() {
        return cola;
    }

    public void setCola(int cola) {
        this.cola = cola;
    }
    
}
