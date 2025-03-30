package domain;

import persistencia.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "jugador")
public class Jugador extends EntidadPersistente {

    @Column(nullable = false, unique = true)
    private String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public Jugador() {
    }

    public Jugador(String nombre, int id) {
        this.nombre = nombre;
        this.setId(id);
    }

    public String getNombre() {
        return nombre;
    }

}
