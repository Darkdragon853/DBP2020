import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// TODO: Alle ManyToManyTabellen mit mehr als zwei Spalten: Jeweils eigene Entitys mit 2 One-To-Many Assoziationen.


public class Main {

    public static void main(String[] args) {

        // Init
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.network.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Start Transaktion
        entityManager.getTransaction().begin();
        System.out.println("\nTransaction starts...\n\n");


        /*Person person = new Person();
        person.setCreationDate(java.sql.Timestamp.valueOf("2017-11-15 15:30:14.332"));
        person.setFirstName("Archibald");
        person.setLastName("Guenther");
        person.setBirthday(java.sql.Date.valueOf("06-08-1999"));
        person.setBrowserUsed("Firefox");
        person.setCity_id(28);
        person.setGender("male");

        String mails[] = new String[2];
        mails[0] = "archibald.guenther@gmx.de";

        String languages[] = new String[2];
        languages[0] = "de";
        languages[1] = "en";

        //person.setEmails(mails);
        //person.setSpeaks(languages);

*/
        // Inhalt

        Place place1 = new Place();
        place1.setName("Meppen");
       // place1.setId(new Long(5));

        Continent continent1 = new Continent();
        continent1.setName("Europa");
        continent1.setUrl("https://eu.eu");

        Country country1 = new Country();
        country1.setName("Deutschland");
        country1.setUrl("https://www.deutschland.de");
        //country1.setContinent_id(continent1.getId());


        City city1 = new City();
        city1.setName("Erfurt");
        city1.setUrl("https://www.erfurt.de");


        System.out.println("\nBefore persist\n\n");
        entityManager.persist(place1);
        System.out.println("\nAfter persist\n\n");


        // Closing
        System.out.println("\nTransaction commits...\n");
        entityManager.getTransaction().commit();
        System.out.println("\nDone!\n");
        entityManagerFactory.close();
    }
}
