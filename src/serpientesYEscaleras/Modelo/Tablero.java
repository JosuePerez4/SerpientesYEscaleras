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

    /*
         * Agrega las serpientes a el arreglo de serpientes, para agregar al tablero
     */
    public boolean sePuedeAgregar(Serpiente serpiente, Escalera escalera) {
        //Para verificar si la serpiente se puede agregar o no
        if (serpiente.getCabeza() < getJuego().length) {
            return true;
        } else if (serpiente.getCabeza() != escalera.getArriba() || serpiente.getCola() != escalera.getAbajo()) {
            return true;
        }
        return false;
    }

    public boolean agregarSerpiente(Serpiente serpiente) {
    int cabeza = serpiente.getCabeza();
    int cola = serpiente.getCola();

    if (cabeza <= 0 || cola <= 0 || cabeza >= juego.length || cola >= juego.length) {
        return false; // La cabeza o la cola están fuera de los límites del tablero.
    }

    if (cola >= cabeza) {
        return false; // La cola está en una posición mayor o igual a la cabeza.
    }

    if (juego[cabeza - 1] != null || juego[cola - 1] != null) {
        return false; // La posición de la cabeza o la cola ya está ocupada.
    }

    serpientes.add(serpiente);
    return true;
}


    /*
         * Agrega las escaleras a el arreglo de serpientes, para agregar al tablero
     */
    public boolean agregarEscalera(Escalera escalera) {
        int base = escalera.getAbajo();
        int cima = escalera.getArriba();

        if (base <= 0 || cima <= 0 || base >= juego.length || cima >= juego.length) {
            return false; // La base o la cima están fuera de los límites del tablero.
        }

        if (base >= cima) {
            return false; // La cima está en una posición menor o igual a la base.
        }

        if (juego[base - 1] != null || juego[cima - 1] != null) {
            return false; // La posición de la base o la cima ya está ocupada.
        }

        escaleras.add(escalera);
        return true;
    }

    public void agregarJugador(Jugador jugador) {
        for (int i = 0; i < getJugadores().length; i++) {
            if (getJugadores()[i] == null) {
                getJugadores()[i] = jugador;
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
            getJuego()[s.getCabeza() - 1] = s; // Se coloca la serpiente en la posición de su cabeza
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

    public String siguienteTurno(int lanzamiento) {
        Jugador jugadorActual = jugadores[turnoActual];
        agregarAlHistorial("Es el turno de: " + jugadorActual.getNombre());
        moverJugador(lanzamiento, jugadorActual);
        turnoActual = (turnoActual + 1) % jugadores.length;
        return ganador() != null ? "El ganador es: " + ganador() : null;
    }

    public void moverJugador(int lanzamiento, Jugador jugadorActual) {
        agregarAlHistorial("El jugador " + jugadorActual.getNombre() + " ha lanzado el dado y ha obtenido " + lanzamiento);
        // Para controlar que los movimientos nunca se pasen del tamaño máximo del tablero
        int nuevaPosicion = jugadorActual.getPosicionActual() + lanzamiento;
        if (nuevaPosicion < getJuego().length) {
            jugadorActual.modificarPosicion(lanzamiento);
            agregarAlHistorial("El jugador ha hecho sus movimientos, su nueva posición es: " + jugadorActual.getPosicionActual());
        } else if (nuevaPosicion == getJuego().length) {
            jugadorActual.modificarPosicion(lanzamiento);
            agregarAlHistorial("El jugador ha hecho sus movimientos, su nueva posición es: " + jugadorActual.getPosicionActual());
            jugadorActual.setGanador(true);
            agregarAlHistorial("El jugador: " + ganador() + " ha ganado el juego");
            //return ganador();
        } else {
            agregarAlHistorial("El jugador " + jugadorActual.getNombre() + " no puede avanzar más y permanece en la posición " + jugadorActual.getPosicionActual());
        }

        // En esta parte se manejan los casos especiales de las serpientes y las escaleras
        Object posicion = getJuego()[jugadorActual.getPosicionActual() - 1];
        if (posicion instanceof Serpiente) {
            agregarAlHistorial("El jugador " + jugadorActual.getNombre() + " encontró una serpiente");
            Serpiente serpiente = (Serpiente) posicion;
            jugadorActual.setPosicionActual(serpiente.getCola());
            agregarAlHistorial("El jugador retrocdedió a la casilla: " + jugadorActual.getPosicionActual());
        } else if (posicion instanceof Escalera) {
            agregarAlHistorial("El jugador " + jugadorActual.getNombre() + " encontró una escalera");
            Escalera escalera = (Escalera) posicion;
            jugadorActual.setPosicionActual(escalera.getArriba());
            agregarAlHistorial("El jugador ascendió a la casilla: " + jugadorActual.getPosicionActual());
        }
        // da el ganador
        //return ganador();
    }

    public void imprimirHistorialJuego() {
        System.out.println("");
        for (String elemento : getHistorialMovimientos()) {
            System.out.println(elemento);
            System.out.println("");
        }
    }

    public void imprimirJugadores() {
        System.out.println("");
        for (int i = 0; i < getJugadores().length; i++) {
            System.out.println(getJugadores()[i].toString());
        }
    }
}
