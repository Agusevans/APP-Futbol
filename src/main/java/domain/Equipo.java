package domain;

import persistencia.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table
public class Equipo extends EntidadPersistente {

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "equipo_jugador",
            joinColumns = @JoinColumn(name = "equipo_id"),
            inverseJoinColumns = @JoinColumn(name = "jugador_id")
    )
    private List<Jugador> jugadores;
    @Column
    private int goles;

    public Equipo(List<Jugador> jugadores, int goles) {
        this.jugadores = jugadores;
        this.goles = goles;
    }

    public Equipo(List<Jugador> jugadores, int goles, int id) {
        this.jugadores = jugadores;
        this.goles = goles;
        this.id = id;
    }

    public Equipo(){
        this.jugadores = new ArrayList<Jugador>();
        this.goles = 0;
    }

    public void mostrarEquipo(){
        System.out.println("Equipo:");
        for (Jugador jugador : jugadores) {
            System.out.println(" - " + jugador.getNombre());
        }
        System.out.println("Goles: " + goles);
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public void agregarJugador(Jugador jugador){
        this.jugadores.add(jugador);
    }

    public void agregarJugadores(Jugador ... jugadores){
        Collections.addAll(this.jugadores, jugadores);
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
