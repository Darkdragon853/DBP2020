import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DBConnection {


    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public DBConnection() {
        // Init
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.network.jpa");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void startTransaction() {
        // Start Transaktion
        entityManager.getTransaction().begin();
        System.out.println("\nTransaction starts...\n\n");
    }

    public void persist(Object object) {
        entityManager.persist(object);
    }

    public void commitTransaction() {
        // Committe Transaktion
        entityManager.getTransaction().commit();
        System.out.println("\nTransaction commited\n");
    }

    public void endConnection() {
        // Closing
        entityManagerFactory.close();
        System.out.println("\nConnection closed\n");
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // returns the List for the given HQL-query. Care, the query is case-sensitve except SELECT, FROM, etc.
    public <T> List<T> getList(String query, Class<T> currentClass) {
        List<T> resultList = entityManager.createQuery(query, currentClass).getResultList();
        return resultList;
    }
}







