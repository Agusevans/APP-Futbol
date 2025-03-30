package persistencia.daos;

import persistencia.EntidadPersistente;
import persistencia.BusquedaCondicional;

import java.util.List;

public class DAOMemoria<T> implements DAO<T> {
    private List<EntidadPersistente> entidades;

    public DAOMemoria(List<EntidadPersistente> entidades){
        this.entidades = entidades;
    }

    @Override
    public List<T> buscarTodos() {
        return (List<T>) this.entidades;
    }

    @Override
    public T buscar(int id) {
        try {
            return (T) this.entidades
                    .stream()
                    .filter(e -> e.getId() == id)
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            //System.out.println("No se encontró la entidad con id: " + id);
            return null;
        }
    }

    @Override
    public T buscar(BusquedaCondicional condicional) {
        try {
            return (T) this.entidades
                    .stream()
                    .filter(condicional.getCondicionPredicado())
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            //System.out.println("No se encontró la entidad con la busqueda condicional");
            return null;
        }
    }

    @Override
    public void agregar(Object unObjeto) {
        this.entidades.add((EntidadPersistente) unObjeto);
    }

    @Override
    public void actualizar(Object unObjeto) {
        //TODO: Revisar
        EntidadPersistente object = (EntidadPersistente) this.buscar(((EntidadPersistente) unObjeto).getId());
        this.entidades.remove(object);
        this.entidades.add((EntidadPersistente) unObjeto);
    }

    @Override
    public void borrar(Object unObjeto) {
        this.entidades.remove(unObjeto);
    }

    @Override
    public void limpiar(){
        this.entidades.clear();
    }
}