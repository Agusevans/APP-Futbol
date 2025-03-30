package persistencia.factories;

import persistencia.config.Config;
import domain.Partido;
import persistencia.daos.DAO;
import persistencia.daos.DAOHibernate;
import persistencia.daos.DAOMemoria;
import persistencia.repositorios.RepoPartidos;
import persistencia.memoData.*;

public class FactoryRepoPartido {
    private static RepoPartidos repo;

    static {
        repo = null;
    }

    public static RepoPartidos get() {
        if (repo == null) {
            if (Config.useDataBase) {
                DAO<Partido> dao = new DAOHibernate<>(Partido.class);
                repo = new RepoPartidos(dao);
            } else {
                repo = new RepoPartidos(new DAOMemoria<>(DataPartido.getDataPartidos()));
            }
        }
        return repo;
    }
}
