package persistencia.factories;

import domain.Cancha;
import persistencia.config.Config;
import persistencia.daos.DAO;
import persistencia.daos.DAOHibernate;
import persistencia.daos.DAOMemoria;
import persistencia.memoData.DataCancha;
import persistencia.repositorios.RepoCanchas;

public class FactoryRepoCanchas {
    private static RepoCanchas repo;

    static {
        repo = null;
    }

    public static RepoCanchas get() {
        if (repo == null) {
            if (Config.useDataBase) {
                DAO<Cancha> dao = new DAOHibernate<>(Cancha.class);
                repo = new RepoCanchas(dao);
            } else {
                repo = new RepoCanchas(new DAOMemoria<>(DataCancha.getDataCanchas()));
            }
        }
        return repo;
    }
}
