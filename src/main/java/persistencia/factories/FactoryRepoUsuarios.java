package persistencia.factories;

import domain.Usuario;
import persistencia.config.Config;
import persistencia.daos.DAO;
import persistencia.daos.DAOHibernate;
import persistencia.daos.DAOMemoria;
import persistencia.memoData.DataUsuario;
import persistencia.repositorios.RepoUsuarios;

public class FactoryRepoUsuarios {

    private static RepoUsuarios repo;

    static {
        repo = null;
    }

    public static RepoUsuarios get() {
        if (repo == null) {
            if (Config.useDataBase) {
                DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
                repo = new RepoUsuarios(dao);
            } else {
                repo = new RepoUsuarios(new DAOMemoria<>(DataUsuario.getDataUsuarios()));
            }
        }
        return repo;
    }
}
