import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        // Init
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.testing.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Start Transaktion
        entityManager.getTransaction().begin();

        // Inhalt

        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }
}
