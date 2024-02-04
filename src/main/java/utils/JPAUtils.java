package utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtils {
    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();
    private static final EntityManager entityManager = getEntityManagerFactory().createEntityManager();
    
    private static EntityManagerFactory buildEntityManagerFactory() {
        try {
            return Persistence.createEntityManagerFactory("jpamysql");
        } catch (Throwable ex) {
            System.err.println("Fallo en la creación de EntityManagerFactory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static EntityManager getEntityManager() {
    	return entityManager;
    }
    
    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static void close() {
        getEntityManagerFactory().close();
        getEntityManager().close();
    }
}
