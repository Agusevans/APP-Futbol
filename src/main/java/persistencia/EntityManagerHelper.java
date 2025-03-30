package persistencia;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EntityManagerHelper {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    static{
        Map<String, String> env = System.getenv();
        Map<String, Object> configOverrides = new HashMap<String, Object>();

        String[] keys = new String[]{
                "javax.persistence.jdbc.url",
                "javax.persistence.jdbc.user",
                "javax.persistence.jdbc.password",
                "DATABASE_URL",
                "hibernate.show_sql",
                "ddlauto",
                "javax.persistence.jdbc.driver",
                "javax.persistence.schema-generation.database.action"
        };

        for (String key : keys) {
            if (env.containsKey(key)) {

                if (key.equals("DATABASE_URL")) {
                    String value = env.get(key);
                    URI dbUri = null;
                    try {
                        dbUri = new URI(value);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }

                    String username = dbUri.getUserInfo().split(":")[0];
                    String password = dbUri.getUserInfo().split(":")[1];
                    //value = "jdbc:MySQL://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
                    value = "jdbc:" + dbUri.getScheme() + "://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
                    configOverrides.put("javax.persistence.jdbc.url", value);
                    configOverrides.put("javax.persistence.jdbc.user", username);
                    configOverrides.put("javax.persistence.jdbc.password", password);
                }

                if (key.equals("ddlauto")) {
                    String value = env.get(key);
                    configOverrides.put("javax.persistence.schema-generation.database.action", value);
                    configOverrides.put("hibernate.hbm2ddl.auto", value);
                }

            }
        }

        try {
            emf = Persistence.createEntityManagerFactory("db", configOverrides);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityManager entityManager() {
        return getEntityManager();
    }

    public static EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }
        return em;
    }

    public static void closeEntityManager() {
        em.close();
        em = null;
    }

    public static void beginTransaction() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        if(!tx.isActive()){
            tx.begin();
        }
    }

    public static void commit() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        if(tx.isActive()){
            tx.commit();
        }

    }

    public static void rollback(){
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        if(tx.isActive()){
            tx.rollback();
        }
    }

    public static Query createQuery(String query) {
        return getEntityManager().createQuery(query);
    }

    public static void persist(Object o){
        entityManager().persist(o);
    }

    public static void withTransaction(Runnable action) {
        withTransaction(() -> {
            action.run();
            return null;
        });
    }
    public static <A> A withTransaction(Supplier<A> action) {
        beginTransaction();
        try {
            A result = action.get();
            commit();
            return result;
        } catch(Throwable e) {
            rollback();
            throw e;
        }
    }
}