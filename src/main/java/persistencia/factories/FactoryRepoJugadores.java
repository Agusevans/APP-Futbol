package persistencia.factories;

import persistencia.config.Config;
import domain.Jugador;
import persistencia.daos.DAO;
import persistencia.daos.DAOHibernate;
import persistencia.daos.DAOMemoria;
import persistencia.memoData.DataJugador;
import persistencia.repositorios.RepoJugadores;

public class FactoryRepoJugadores {

    private static RepoJugadores repo;

    static {
        repo = null;
    }

    public static RepoJugadores get() {
        if (repo == null) {
            if (Config.useDataBase) {
                DAO<Jugador> dao = new DAOHibernate<>(Jugador.class);
                repo = new RepoJugadores(dao);
            } else {
                repo = new RepoJugadores(new DAOMemoria<>(DataJugador.getDataJugadores()));
            }
        }
        return repo;
    }
}
