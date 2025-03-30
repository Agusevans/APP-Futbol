package persistencia.repositorios;

import domain.Usuario;
import persistencia.BusquedaCondicional;
import persistencia.config.Config;
import persistencia.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepoUsuarios extends Repositorio<Usuario>{

    public RepoUsuarios(DAO<Usuario> dao) {
        super(dao);
    }

    public Boolean existe(String nombreDeUsuario){
        return this.buscarUsuario(nombreDeUsuario) != null;
    }

    public Usuario buscarUsuario(String nombreDeUsuario, String contrasenia){
        return this.dao.buscar(condicionUsuarioYContrasenia(nombreDeUsuario, contrasenia));
    }

    public Usuario buscarUsuario(String nombreDeUsuario){
        return this.dao.buscar(condicionUsuario(nombreDeUsuario));
    }

    private BusquedaCondicional condicionUsuarioYContrasenia(String nombreDeUsuario, String contrasenia){

        java.util.function.Predicate<Usuario> condicionUsuarioYContraseniaMemo = null;
        CriteriaQuery<Usuario> usuarioQuery = null; //TODO: MEJORAR EN TODOS LOS REPOS
        if(Config.useDataBase) {
            CriteriaBuilder criteriaBuilder = criteriaBuilder();
            usuarioQuery = criteriaBuilder.createQuery(Usuario.class);
            Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);
            Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("nombreUsuario"), nombreDeUsuario);
            Predicate condicionContrasenia = criteriaBuilder.equal(condicionRaiz.get("contrasenia"), contrasenia);
            Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);
            usuarioQuery.where(condicionExisteUsuario);
        }
        else
            condicionUsuarioYContraseniaMemo = (usuario -> usuario.getNombreUsuario().equals(nombreDeUsuario) && usuario.getContrasenia().equals(contrasenia));

        return new BusquedaCondicional(condicionUsuarioYContraseniaMemo, usuarioQuery);
    }

    private BusquedaCondicional condicionUsuario(String nombreDeUsuario){
        CriteriaQuery<Usuario> usuarioQuery = null; //TODO: MEJORAR EN TODOS LOS REPOS
        java.util.function.Predicate<Usuario> condicionNombreDeUsuarioMemo = null;
        if (Config.useDataBase) {
            CriteriaBuilder criteriaBuilder = criteriaBuilder();
            usuarioQuery = criteriaBuilder.createQuery(Usuario.class);
            Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);
            Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("nombreUsuario"), nombreDeUsuario);
            usuarioQuery.where(condicionNombreDeUsuario);
        }
        else
            condicionNombreDeUsuarioMemo = (usuario -> usuario.getNombreUsuario().equals(nombreDeUsuario));

        return new BusquedaCondicional(condicionNombreDeUsuarioMemo, usuarioQuery);
    }

}
