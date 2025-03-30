package persistencia.repositorios;

import persistencia.EntityManagerHelper;
import persistencia.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class Repositorio<T> {
    protected DAO<T> dao;

    public Repositorio(DAO<T> dao) {
        this.dao = dao;
    }

    public void setDao(DAO<T> dao) {
        this.dao = dao;
    }

    public void agregar(Object unObjeto){
        this.dao.agregar(unObjeto);
    }

    public void actualizar(Object unObjeto){
        this.dao.actualizar(unObjeto);
    }

    public void borrar(Object unObjeto){
        this.dao.borrar(unObjeto);
    }

    public List<T> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public T buscar(int id){
        return this.dao.buscar(id);
    }

    public CriteriaBuilder criteriaBuilder(){
        return EntityManagerHelper.getEntityManager().getCriteriaBuilder();
    }

    public void limpiar(){
        this.dao.limpiar();
    }
}