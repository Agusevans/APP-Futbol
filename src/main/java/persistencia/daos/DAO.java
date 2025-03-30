package persistencia.daos;

import persistencia.BusquedaCondicional;

import java.util.List;

public interface DAO<T> {
    List<T> buscarTodos();
    T buscar(int id);
    T buscar(BusquedaCondicional condicional);
    void agregar(Object unObjeto);
    void actualizar(Object unObjeto);
    void borrar(Object unObjeto);
    void limpiar();
}