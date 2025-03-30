package persistencia.repositorios;

import domain.Cancha;
import persistencia.BusquedaCondicional;
import persistencia.config.Config;
import persistencia.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepoCanchas extends Repositorio<Cancha>{

    public RepoCanchas(DAO<Cancha> dao) {
        super(dao);
    }

    public Cancha buscar(String nombre) {
        return this.dao.buscar(this.condicionNombre(nombre));
    }

    public boolean existe(String nombre) {
        return this.buscar(nombre)!= null;
    }

    private BusquedaCondicional condicionNombre(String nombre){

        CriteriaQuery<Cancha> canchaQuery = null; //TODO: MEJORAR EN TODOS LOS REPOS
        java.util.function.Predicate<Cancha> condicionNombreDeCanchaMemo = null;
        if(Config.useDataBase) {
            CriteriaBuilder criteriaBuilder = criteriaBuilder();
            canchaQuery = criteriaBuilder.createQuery(Cancha.class);
            Root<Cancha> condicionRaiz = canchaQuery.from(Cancha.class);
            Predicate condicionNombreDeCancha = criteriaBuilder.equal(condicionRaiz.get("nombre"), nombre);
            canchaQuery.where(condicionNombreDeCancha);
        }
        else
            condicionNombreDeCanchaMemo = (cancha -> cancha.getNombre().equals(nombre));

        return new BusquedaCondicional(condicionNombreDeCanchaMemo, canchaQuery);
    }

}
