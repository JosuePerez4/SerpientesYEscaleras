/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serpientesYEscaleras.Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author monok
 */
public class Tablero {

    private Object[] juego;
    private List<Serpiente> serpientes;
    private List<Escalera> escaleras;
    private Jugador[] jugadores;
    private Dado dado;
    private List<String> historialMovimientos;
    private int turnoActual;

    public Tablero() {

    }

    public Tablero(int tamañoTablero, int cantidadJugadores) {
        this.juego = new Object[tamañoTablero];
        this.serpientes = new ArrayList<>();
        this.escaleras = new ArrayList<>();
        this.jugadores = new Jugador[cantidadJugadores];
        this.dado = new Dado();
        this.historialMovimientos = new ArrayList<>();
    }

    public Object[] getJuego() {
        return juego;
    }

    public void setJuego(Object[] juego) {
        this.juego = juego;
    }

    public List<Serpiente> getSerpientes() {
        return serpientes;
    }

    public void setSerpientes(List<Serpiente> serpientes) {
        this.serpientes = serpientes;
    }

    public List<Escalera> getEscaleras() {
        return escaleras;
    }

    public void setEscaleras(List<Escalera> escaleras) {
        this.escaleras = escaleras;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }

    public Dado getDado() {
        return dado;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }

    public List<String> getHistorialMovimientos() {
        return historialMovimientos;
    }

    public void setHistorialMovimientos(List<String> historialMovimientos) {
        this.historialMovimientos = historialMovimientos;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public void setTurnoActual(int turnoActual) {
        this.turnoActual = turnoActual;
    }

    // Método para verificar el tipo de casilla
    public int verificarCasilla(int posicion) {
        if (posicion <= 0 || posicion > juego.length) {
            throw new IllegalArgumentException("Posición fuera de los límites del tablero.");
        }
        Object casilla = juego[posicion - 1];
        if (casilla instanceof Serpiente) {
            return 1;
        } else if (casilla instanceof Escalera) {
            return 2;
        } else {
            return 0;
        }
    }

    /*
     * Agrega las serpientes a el arreglo de serpientes, para agregar al tablero
     */
    public boolean agregarSerpiente(int cabeza, int cola) {

        if (cabeza <= 0 || cola <= 0 || cabeza >= juego.length || cola >= juego.length) {
            return false; // La cabeza o la cola están fuera de los límites del tablero.
        }

        if (cola >= cabeza) {
            return false; // La cola está en una posición mayor o igual a la cabeza.
        }

        if (juego[cabeza - 1] != null || juego[cola - 1] != null) {
            return false; // La posición de la cabeza o la cola ya está ocupada.
        }

        Serpiente serpiente = new Serpiente(cabeza, cola);
        serpientes.add(serpiente);
        return true;
    }


    /*
         * Agrega las escaleras a el arreglo de serpientes, para agregar al tablero
     */
    public boolean agregarEscalera(int base, int cima) {

        if (base <= 0 || cima <= 0 || base >= juego.length || cima >= juego.length) {
            return false; // La base o la cima están fuera de los límites del tablero.
        }

        if (base >= cima) {
            return false; // La cima está en una posición menor o igual a la base.
        }

        if (juego[base - 1] != null || juego[cima - 1] != null) {
            return false; // La posición de la base o la cima ya está ocupada.
        }

        Escalera escalera = new Escalera(cima, base);
        escaleras.add(escalera);
        return true;
    }

    public void agregarJugador(String nombre) {
        for (int i = 0; i < getJugadores().length; i++) {
            if (getJugadores()[i] == null) {
                Jugador j = new Jugador(nombre);
                getJugadores()[i] = j;
                break;
            }
        }
    }

    public void agregarAlHistorial(String movimiento) {
        getHistorialMovimientos().add(movimiento);
    }

    public void rellenarTablero() {
        // Se rellena el tablero con las serpientes
        for (Serpiente s : getSerpientes()) {
            juego[s.getCabeza() - 1] = s; // Se coloca la serpiente en la posición de su cabeza
        }
        // Se rellena el tablero con las escaleras
        for (Escalera e : getEscaleras()) {
            getJuego()[e.getAbajo() - 1] = e; // Se coloca la escalera en la posición de su escalón inferior
        }
        // Se rellena el resto del tablero
        for (int i = 0; i < getJuego().length; i++) {
            if (getJuego()[i] == null) {
                getJuego()[i] = i + 1; // Se agrega cada número
            }
        }
    }

    public void imprimirTablero() {
        for (int i = 0; i < getJuego().length; i++) {
            if (getJuego()[i] instanceof Serpiente) {
                System.out.print("S ");
            } else if (getJuego()[i] instanceof Escalera) {
                System.out.print("E ");
            } else {
                System.out.print(getJuego()[i] + " ");
            }
        }
    }

    public String ganador() {
        String s = null;
        for (int i = 0; i < getJugadores().length; i++) {
            if (getJugadores()[i].isGanador()) {
                s = getJugadores()[i].getNombre();
                return s;
            }
        }
        return s;
    }

    public int realizarLanzamiento() {
        return getDado().lanzar();
    }

    public String siguienteTurno() {
        turnoActual = (turnoActual + 1) % jugadores.length;
        return ganador() != null ? "El ganador es: " + ganador() : null;
    }

    public void moverJugador(int lanzamiento, Jugador jugadorActual) {
        agregarAlHistorial("El jugador " + jugadorActual.getNombre() + " ha lanzado el dado y ha obtenido " + lanzamiento);
        int nuevaPosicion = jugadorActual.getPosicionActual() + lanzamiento;

        if (nuevaPosicion <= getJuego().length) {
            jugadorActual.modificarPosicion(lanzamiento);
            agregarAlHistorial("El jugador ha hecho sus movimientos, su nueva posición es: " + jugadorActual.getPosicionActual());
        } else {
            agregarAlHistorial("El jugador " + jugadorActual.getNombre() + " no puede avanzar más y permanece en la posición " + jugadorActual.getPosicionActual());
            return;
        }

        Object posicion = getJuego()[jugadorActual.getPosicionActual() - 1];
        if (posicion instanceof Serpiente) {
            Serpiente serpiente = (Serpiente) posicion;
            agregarAlHistorial("El jugador " + jugadorActual.getNombre() + " encontró una serpiente y descendió de la casilla "
                    + serpiente.getCabeza() + " a la casilla " + serpiente.getCola());
            jugadorActual.setPosicionActual(serpiente.getCola());
        } else if (posicion instanceof Escalera) {
            Escalera escalera = (Escalera) posicion;
            agregarAlHistorial("El jugador " + jugadorActual.getNombre() + " encontró una escalera y ascendió de la casilla "
                    + escalera.getAbajo() + " a la casilla " + escalera.getArriba());
            jugadorActual.setPosicionActual(escalera.getArriba());
        }

        if (jugadorActual.getPosicionActual() == getJuego().length) {
            jugadorActual.setGanador(true);
            agregarAlHistorial("El jugador " + jugadorActual.getNombre() + " ha ganado el juego");
        }
    }

    public String obtenerHistorialJuego() {
        StringBuilder historial = new StringBuilder();
        for (String movimiento : getHistorialMovimientos()) {
            historial.append(movimiento).append("\n");
        }
        return historial.toString();
    }

    public void imprimirHistorialJuegoConsola() {
        System.out.println("");
        for (String elemento : getHistorialMovimientos()) {
            System.out.println(elemento);
            System.out.println("");
        }
    }

    public void imprimirJugadores() {
        System.out.println("");
        for (int i = 0; i < getJugadores().length; i++) {
            System.out.println(jugadores[i].toString());
        }
    }
}
