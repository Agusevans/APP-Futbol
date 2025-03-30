package controllers;

import domain.Jugador;
import persistencia.factories.FactoryRepoJugadores;
import persistencia.repositorios.RepoJugadores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JugadorController {

    private final RepoJugadores repoJugadores;

    public JugadorController(){
        this.repoJugadores = FactoryRepoJugadores.get();
    }

    public ModelAndView mostrarJugadores(Request request, Response response){

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", request.session().attribute("usuario"));
        try {
            List<Jugador> jugadores = this.repoJugadores.buscarTodos();
            parametros.put("jugadores", jugadores);
        }
        catch (Exception e) {
            response.body("Error al intentar mostrar los jugadores: " + e.getMessage());
            response.status(500);
            parametros.put("mensaje_error", "Error al intentar mostrar los jugadores");
            return new ModelAndView(parametros, "error_server.hbs");
        }

        return new ModelAndView(parametros, "jugadores.hbs");
    }

    public Response agregarJugador(Request request, Response response) {

        String nombre = request.queryParams("nombre");

        try {
            if (this.repoJugadores.existe(nombre)) {
                response.body("El jugador" + nombre + "ya existe");
                response.status(500);
                response.redirect("/jugadores/gestion?error=true");
            }
            else {
                Jugador jugador = new Jugador(nombre);
                this.repoJugadores.agregar(jugador);
                response.redirect("/jugadores");
            }
        } catch (Exception e) {
            response.body("Error al intentar registrar el jugador: " + e.getMessage());
            response.status(500);
            response.redirect("/jugadores/gestion?error=true");
        }

        return response;
    }

    public ModelAndView gestionJugadores(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", request.session().attribute("usuario"));
        if (request.queryParams("error") != null)
            parametros.put("error", true);
        return new ModelAndView(parametros, "alta_jugador.hbs");
    }

}
