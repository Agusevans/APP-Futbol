package persistencia.memoData;

import domain.*;
import persistencia.EntidadPersistente;
import java.util.ArrayList;
import java.util.List;

public class DataUsuario {
    private static List<Usuario> usuarios = new ArrayList<>();

    static {
        int id = 1;
        usuarios.add(new Usuario("admin", "admin",id++));
        usuarios.add(new Usuario("user", "user",id++));
    }

    public static List<EntidadPersistente> getDataUsuarios(){
        return (List<EntidadPersistente>)(List<?>) usuarios;
    }

}
