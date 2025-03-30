package persistencia.repositorios;

import domain.Usuario;
import persistencia.BusquedaCondicional;
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
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);
        Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("nombreUsuario"), nombreDeUsuario);
        Predicate condicionContrasenia = criteriaBuilder.equal(condicionRaiz.get("contrasenia"), contrasenia);
        Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);
        usuarioQuery.where(condicionExisteUsuario);

        java.util.function.Predicate<Usuario> condicionUsuarioYContraseniaMemo = (usuario -> usuario.getNombreUsuario().equals(nombreDeUsuario) && usuario.getContrasenia().equals(contrasenia));

        return new BusquedaCondicional(condicionUsuarioYContraseniaMemo, usuarioQuery);
    }

    private BusquedaCondicional condicionUsuario(String nombreDeUsuario){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);
        Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("nombreUsuario"), nombreDeUsuario);
        usuarioQuery.where(condicionNombreDeUsuario);

        java.util.function.Predicate<Usuario> condicionNombreDeUsuarioMemo = (usuario -> usuario.getNombreUsuario().equals(nombreDeUsuario));

        return new BusquedaCondicional(condicionNombreDeUsuarioMemo, usuarioQuery);
    }

}
