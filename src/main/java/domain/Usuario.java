package domain;

import persistencia.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadPersistente {
    @Column(name = "usuario",nullable = false, unique = true)
    private String nombreUsuario;
    @Column(name = "contrasenia",nullable = false)
    private String contrasenia;

    public Usuario(String nombreUsuario, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public Usuario(String nombreUsuario, String contrasenia, int id) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.id = id;
    }

    public Usuario() {
    }

    public boolean esAdmin() {
        //TODO: Implementar bien
        return this.nombreUsuario.equals("admin");
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }
}
