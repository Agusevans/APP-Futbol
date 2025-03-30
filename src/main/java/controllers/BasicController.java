package controllers;

import domain.Usuario;
import persistencia.factories.FactoryRepoUsuarios;
import persistencia.repositorios.RepoUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class BasicController {

    private final RepoUsuarios repoUsuarios;

    public BasicController(){
        this.repoUsuarios = FactoryRepoUsuarios.get();
    }

    public ModelAndView index(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", request.session().attribute("usuario"));
        parametros.put("admin", request.session().attribute("admin"));
        return new ModelAndView(parametros, "index.hbs");
    }

    public ModelAndView register(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        if (request.queryParams("error") != null)
            parametros.put("error", true);
        return new ModelAndView(parametros, "alta_usuario.hbs");
    }

    public Response registerUser(Request request, Response response){
        try {
            String nombreUsuario = request.queryParams("usuario");
            String contrasenia = request.queryParams("contrasenia");

            if(this.repoUsuarios.existe(nombreUsuario)){
                response.body("El usuario " + nombreUsuario + " ya existe");
                response.status(500);
                response.redirect("/register?error=true");
                return response;
            }
            else {
                Usuario usuario = new Usuario(nombreUsuario, contrasenia);
                this.repoUsuarios.agregar(usuario);
                response.redirect("/login");
            }
        }
        catch(Exception e){
            response.body("Error al intentar registrar el usuario: " + e.getMessage());
            response.status(500);
            response.redirect("/register?error=true");
        }

        return response;
    }

    public Response logout(Request request, Response response){
        request.session().invalidate();
        response.redirect("/login");
        return response;
    }

    public ModelAndView login(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        if (request.queryParams("error") != null)
            parametros.put("error", true);
        return new ModelAndView(parametros, "login.hbs");
    }

    public Response loginUser(Request request, Response response){
        try {
            String nombreUsuario = request.queryParams("usuario");
            String contrasenia = request.queryParams("contrasenia");

            Usuario usuario = this.repoUsuarios.buscarUsuario(nombreUsuario, contrasenia);

            if(usuario == null){
                response.body("Usuario o contraseña incorrectos");
                response.status(401);
                response.redirect("/login?error=true");
            }
            else {
                request.session(true);
                request.session().attribute("id", usuario.getId());
                request.session().attribute("usuario", usuario.getNombreUsuario());
                request.session().attribute("admin", usuario.esAdmin());
                response.redirect("/index");
            }
        }
        catch (Exception e) {
            response.body("Error al intentar loguearse: " + e.getMessage());
            response.status(500);
            response.redirect("/login?error=true");
        }

        return response;
    }

    public void verificarSesion(Request request, Response response){
        if(!this.estaLogueadoUsuario(request))
            response.redirect("/login");
    }

    public boolean estaLogueadoUsuario(Request request){
        return request.session().attribute("id") != null;
    }

    public void verificarSesionAdmin(Request request, Response response){
        boolean estaLogueado = this.estaLogueadoUsuario(request);
        boolean esAdmin = request.session().attribute("admin") != null;

        if(!estaLogueado){
            response.redirect("/login");
        } else if (!esAdmin) {
            response.redirect("/error");
        }

    }

    public Response verificarCredenciales(Request request, Response response){ //TODO
        try {
            String nombreUsuario = request.queryParams("usuario");
            String contrasenia = request.queryParams("contrasenia");

            Usuario usuario = this.repoUsuarios.buscarUsuario(nombreUsuario, contrasenia);

            if(usuario == null){
                response.body("Usuario o contraseña incorrectos");
                response.status(401);
            }
            else {
                response.body("Usuario validado correctamente");
                response.status(200);
            }
        }
        catch (Exception e) {
            response.body("Error al validar usuario: " + e.getMessage());
            response.status(500);
        }
        return response;
    }

    public ModelAndView error(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("error_message", request.body());
        return new ModelAndView(parametros, "error.hbs");
    }

}
