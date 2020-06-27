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

        Person person = new Person();
        person.setCreationDate(...);
        person.setFirstName("Archibald");
        person.setLastName("Guenther");
        person.setBirthday(...);
        person.setBrowserUsed("Firefox");
        person.setCity_id(28);
        person.setGender("male");

        String mails[];
        mails[0] = "archibald.guenther@gmx.de";

        String languages[];
        languages[0] = "de";
        languages[1] = "en";

        person.setEmails(mails);
        person.setSpeaks(languages);


        // Inhalt

        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }
}
