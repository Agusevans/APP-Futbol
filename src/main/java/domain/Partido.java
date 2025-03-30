package domain;

import persistencia.EntidadPersistente;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "partido")
public class Partido extends EntidadPersistente {
    @OneToOne(cascade = CascadeType.ALL)
    private Equipo equipo1;
    @OneToOne(cascade = CascadeType.ALL)
    private Equipo equipo2;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cancha_id", referencedColumnName = "id")
    private Cancha cancha;
    @Column
    private LocalDateTime fecha;

    public Partido(Equipo equipo1, Equipo equipo2, Cancha cancha, LocalDateTime fecha) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.cancha = cancha;
        this.fecha = fecha;
    }

    public Partido(Equipo equipo1, Equipo equipo2, Cancha cancha, LocalDateTime fecha, int id) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.cancha = cancha;
        this.fecha = fecha;
        this.id = id;
    }

    public Partido(Cancha cancha, LocalDateTime fecha) {
        this.cancha = cancha;
        this.fecha = fecha;
    }

    public Partido() {
    }

    public Equipo getGanador(){
        if (this.equipo1.getGoles() > this.equipo2.getGoles())
            return this.equipo1;
        if (this.equipo1.getGoles() < this.equipo2.getGoles())
            return this.equipo2;
        return null;
    }

    public Equipo getPerdedor(){
        if (this.equipo1.getGoles() > this.equipo2.getGoles())
            return this.equipo2;
        if (this.equipo1.getGoles() < this.equipo2.getGoles())
            return this.equipo1;
        return null;
    }

    public int getDG(){
        return Math.abs(this.equipo1.getGoles() - this.equipo2.getGoles());
    }

    public boolean empate(){
        return this.getDG() == 0;
    }

    public void mostrarPartido(){

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        System.out.println("Partido en " + this.cancha.getNombre() + ", fecha: " + formatoFecha.format(this.fecha));

        if(this.empate()){
            System.out.println("Empate");
            this.equipo1.mostrarEquipo();
            this.equipo2.mostrarEquipo();
        }
        else {
            Equipo ganador = this.getGanador();
            Equipo perdedor = this.getPerdedor();
            System.out.println("Ganador: ");
            ganador.mostrarEquipo();
            System.out.println("Perdedor: ");
            perdedor.mostrarEquipo();
            System.out.println("Diferencia de goles de " + this.getDG());
        }

    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}
