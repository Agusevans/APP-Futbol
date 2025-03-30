package persistencia.repositorios;

import domain.Jugador;
import persistencia.BusquedaCondicional;
import persistencia.config.Config;
import persistencia.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RepoJugadores extends Repositorio<Jugador>{

    public RepoJugadores(DAO<Jugador> dao) {
        super(dao);
    }

    public Jugador buscar(String nombre) {
        return this.dao.buscar(this.condicionNombre(nombre));
    }

    public boolean existe(String nombre) {
        return this.buscar(nombre)!= null;
    }

    private BusquedaCondicional condicionNombre(String nombre){

        CriteriaQuery<Jugador> jugadorQuery = null; //TODO: MEJORAR EN TODOS LOS REPOS
        java.util.function.Predicate<Jugador> condicionNombreMemo = null;
        if (Config.useDataBase) {
            CriteriaBuilder criteriaBuilder = criteriaBuilder();
            jugadorQuery = criteriaBuilder.createQuery(Jugador.class);
            Root<Jugador> condicionRaiz = jugadorQuery.from(Jugador.class);
            Predicate condicionNombre = criteriaBuilder.equal(condicionRaiz.get("nombre"), nombre);
            jugadorQuery.where(condicionNombre);
        }
        else
            condicionNombreMemo = (jugador -> jugador.getNombre().equals(nombre));

        return new BusquedaCondicional(condicionNombreMemo, jugadorQuery);
    }

    public List<Jugador> buscarJugadoresPorIDs(String[] ids){
        List<Jugador> jugadoresList = new ArrayList<>();

        if (ids != null) {
            for (String id : ids) {
                Jugador jugador = this.buscar(Integer.parseInt(id));
                jugadoresList.add(jugador);
            }
        }
        return jugadoresList;
    }

}
