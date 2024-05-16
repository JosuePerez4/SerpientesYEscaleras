/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serpientesYEscaleras.Modelo;

/**
 *
 * @author monok
 */
public class Escalera {

    private int arriba;
    private int abajo;
    
    public Escalera (int arriba, int abajo) {
        this.arriba = arriba;
        this.abajo = abajo;
    }

    public int getArriba() {
        return arriba;
    }

    public void setArriba(int arriba) {
        this.arriba = arriba;
    }

    public int getAbajo() {
        return abajo;
    }

    public void setAbajo(int abajo) {
        this.abajo = abajo;
    }

}
