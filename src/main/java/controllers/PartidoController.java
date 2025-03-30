package controllers;

import domain.Cancha;
import domain.Equipo;
import domain.Jugador;
import domain.Partido;
import persistencia.factories.FactoryRepoCanchas;
import persistencia.factories.FactoryRepoJugadores;
import persistencia.factories.FactoryRepoPartido;
import persistencia.repositorios.RepoCanchas;
import persistencia.repositorios.RepoJugadores;
import persistencia.repositorios.RepoPartidos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDateTime;
import java.util.*;

public class PartidoController {

    private final RepoPartidos repoPartidos;
    private final RepoJugadores repoJugadores;
    private final RepoCanchas repoCanchas;

    public PartidoController(){
        this.repoPartidos = FactoryRepoPartido.get();
        this.repoJugadores = FactoryRepoJugadores.get();
        this.repoCanchas = FactoryRepoCanchas.get();
    }

    public ModelAndView mostrarPartidos(Request request, Response response){

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", request.session().attribute("usuario"));
        try {
            List<Partido> partidos = this.repoPartidos.buscarTodos();
            parametros.put("partidos", partidos);
        }
        catch (Exception e) {
            response.body("Error al intentar mostrar los partidos: " + e.getMessage());
            response.status(500);
            parametros.put("mensaje_error", "Error al intentar mostrar los partidos");
            return new ModelAndView(parametros, "error_server.hbs");
        }

        return new ModelAndView(parametros, "partidos.hbs");
    }

    public Response agregarPartido(Request request, Response response){ //

        //this.logAltaPartido(request);

        try {
            LocalDateTime fecha = LocalDateTime.parse(request.queryParams("fecha"));

            int canchaID = Integer.parseInt(request.queryParams("cancha"));
            Cancha cancha = this.repoCanchas.buscar(canchaID);

            int goles1 = Integer.parseInt(request.queryParams("goles1"));
            int goles2 = Integer.parseInt(request.queryParams("goles2"));

            String[] jugadores1_ids = request.queryParamsValues("equipo1[]");
            String[] jugadores2_ids = request.queryParamsValues("equipo2[]");
            List<Jugador> jugadores1 = this.repoJugadores.buscarJugadoresPorIDs(jugadores1_ids);
            List<Jugador> jugadores2 = this.repoJugadores.buscarJugadoresPorIDs(jugadores2_ids);

            Equipo equipo1 = new Equipo(jugadores1, goles1);
            Equipo equipo2 = new Equipo(jugadores2, goles2);

            Partido partido = new Partido(equipo1, equipo2, cancha, fecha);

            this.repoPartidos.agregar(partido);

            response.redirect("/partidos");
        }
        catch (Exception e) {
            response.body("Error al registrar el partido: " + e.getMessage());
            response.status(500);
            response.redirect("/partidos/gestion?error=true");
        }

        return response;
    }

    public ModelAndView gestionPartidos(Request request, Response response){

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", request.session().attribute("usuario"));
        try {
            List<Jugador> jugadores = this.repoJugadores.buscarTodos();
            List<Cancha> canchas = this.repoCanchas.buscarTodos();
            parametros.put("canchas", canchas);
            parametros.put("jugadores", jugadores);
        }
        catch (Exception e) {
            response.body("Error al intentar mostrar los jugadores: " + e.getMessage());
            response.status(500);
            parametros.put("mensaje_error", "Error al intentar mostrar los jugadores");
            return new ModelAndView(parametros, "error_server.hbs");
        }

        return new ModelAndView(parametros, "alta_partido.hbs");
    }

    public String reset(Request request, Response response){
        this.repoPartidos.limpiar();
        this.repoJugadores.limpiar();
        this.repoCanchas.limpiar();
        return "Datos de partidos reseteados";
    }

    public String load(Request request, Response response){
        this.repoPartidos.load();
        return "Datos de partidos cargados correctamente";
    }

    public void logAltaPartido(Request request){
        String[] values;
        for (String key : request.queryParams()) {
            if(key.equals(key + "[]")) {
                System.out.println("Parámetro: " + key);
                values = request.queryParamsValues(key);
                for (String value : values) {
                    System.out.println("values: " + value);
                }
            } else {
                System.out.println("Parámetro: " + key + " = " + request.queryParams(key));
            }
        }
    }

}
