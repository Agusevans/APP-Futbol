package persistencia.daos;

import persistencia.BusquedaCondicional;
import persistencia.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class DAOHibernate<T> implements DAO<T> {
    private Class<T> type;

    public DAOHibernate(Class<T> type){
        this.type = type;
    }

    @Override
    public List<T> buscarTodos() {
        CriteriaBuilder builder = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> critera = builder.createQuery(this.type);
        critera.from(type);
        return EntityManagerHelper.getEntityManager().createQuery(critera).getResultList();
    }

    @Override
    public T buscar(int id) {
        try {
            return EntityManagerHelper.getEntityManager().find(type, id);
        } catch (Exception e) {
            //System.out.println("No se encontró la entidad con id: " + id);
            return null;
        }
    }

    @Override
    public T buscar(BusquedaCondicional condicional) {
        try {
            return (T) EntityManagerHelper.getEntityManager()
                    .createQuery(condicional.getCondicionCriterio())
                    //.getSingleResult();
                    .getResultList()
                    .get(0);
        } catch (Exception e) {
            //System.out.println("No se encontró la entidad con la busqueda condicional");
            return null;
        }
    }

    @Override
    public void agregar(Object unObjeto) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(unObjeto);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    @Override
    public void actualizar(Object unObjeto) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(unObjeto);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    @Override
    public void borrar(Object unObjeto) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(unObjeto);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    @Override
    public void limpiar() {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        try {
            EntityManagerHelper.getEntityManager().createQuery("DELETE FROM " + this.type.getSimpleName()).executeUpdate();
        }
        catch (Exception e) {
            EntityManagerHelper.getEntityManager().getTransaction().rollback();
        }
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }
}