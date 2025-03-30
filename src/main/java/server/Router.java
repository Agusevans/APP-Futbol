package server;

import controllers.BasicController;
import controllers.CanchaController;
import controllers.PartidoController;
import controllers.JugadorController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure(){

        BasicController basicControler = new BasicController();
        Spark.get("/index", basicControler::index, Router.engine);
        Spark.get("/login", basicControler::login, Router.engine);
        Spark.post("/login", basicControler::loginUser);
        Spark.get("/logout", basicControler::logout);
        Spark.get("/register", basicControler::register, Router.engine);
        Spark.post("/usuario", basicControler::registerUser);
        Spark.get("/error", basicControler::error, Router.engine);

        Spark.before("/index", basicControler::verificarSesion);
        Spark.before("/error", basicControler::verificarSesion);
        Spark.before("/partidos", basicControler::verificarSesion);
        Spark.before("/partidos/gestion", basicControler::verificarSesionAdmin);
        Spark.before("/jugadores", basicControler::verificarSesion);
        Spark.before("/jugadores/gestion", basicControler::verificarSesionAdmin);
        Spark.before("/canchas", basicControler::verificarSesion);
        Spark.before("/canchas/gestion", basicControler::verificarSesionAdmin);

        CanchaController canchaController = new CanchaController();
        Spark.get("/canchas", canchaController::mostrarCanchas, Router.engine);
        Spark.post("/canchas", canchaController::agregarCancha);
        Spark.get("/canchas/gestion", canchaController::gestionCanchas, Router.engine);

        PartidoController partidoController = new PartidoController();
        Spark.get("/partidos", partidoController::mostrarPartidos, Router.engine);
        Spark.post("/partidos", partidoController::agregarPartido);
        Spark.get("/partidos/gestion", partidoController::gestionPartidos, Router.engine);

        //Spark.post("partidos/reset", partidoController::reset); //TODO: Ver como implementar
        Spark.post("partidos/load", partidoController::load); //TODO: Ver como implementar

        JugadorController jugadorController = new JugadorController();
        Spark.post("/jugadores", jugadorController::agregarJugador);
        Spark.get("/jugadores", jugadorController::mostrarJugadores, Router.engine);
        Spark.get("/jugadores/gestion", jugadorController::gestionJugadores, Router.engine);

    }
}