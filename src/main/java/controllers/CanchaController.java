package controllers;

import domain.Cancha;
import persistencia.factories.FactoryRepoCanchas;
import persistencia.repositorios.RepoCanchas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanchaController {

    private final RepoCanchas repoCanchas;

    public CanchaController(){
        this.repoCanchas = FactoryRepoCanchas.get();
    }

    public ModelAndView mostrarCanchas(Request request, Response response){

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", request.session().attribute("usuario"));
        try {
            List<Cancha> canchas = this.repoCanchas.buscarTodos();
            parametros.put("canchas", canchas);
        }
        catch (Exception e) {
            response.body("Error al intentar mostrar las canchas: " + e.getMessage());
            response.status(500);
            parametros.put("mensaje_error", "Error al intentar mostrar las canchas");
            return new ModelAndView(parametros, "error_server.hbs");
        }

        return new ModelAndView(parametros, "canchas.hbs");
    }

    public Response agregarCancha(Request request, Response response){

        String nombre = request.queryParams("nombre");
        int capacidad = Integer.parseInt(request.queryParams("capacidad"));

        try {
            if (this.repoCanchas.existe(nombre)) {
                response.body("La cancha " + nombre + " ya existe");
                response.status(500);
                response.redirect("/canchas/gestion?error=true");
            }
            else {
                Cancha cancha = new Cancha(nombre, capacidad);
                this.repoCanchas.agregar(cancha);
                response.redirect("/canchas");
            }
        }
        catch (Exception e) {
            response.body("Error al registrar la cancha: " + e.getMessage());
            response.status(500);
            response.redirect("/canchas/gestion?error=true" + e.getMessage());
        }

        return response;
    }

    public ModelAndView gestionCanchas(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", request.session().attribute("usuario"));

        if (request.queryParams("error") != null)
            parametros.put("error", true);

        return new ModelAndView(parametros, "alta_cancha.hbs");
    }

}
