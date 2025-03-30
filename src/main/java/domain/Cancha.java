package domain;

import persistencia.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cancha")
public class Cancha extends EntidadPersistente {

    @Column(nullable = false, unique = true)
    private String nombre;
    @Column(nullable = false)
    private int capacidad;

    public Cancha(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public Cancha(String nombre, int capacidad, int id) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.id = id;
    }

    public Cancha() {
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }
}
