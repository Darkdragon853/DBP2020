import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

// TODO: Temporal types


public class Main {

    public static void main(String[] args) {
        DBConnection database = new DBConnection();
        database.startTransaction();





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
/*
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

        // Persist
        System.out.println("\nBefore persist:\n\n");
        database.persist(place1);
        System.out.println("\nAfter persist:\n\n");

        // Commit
        System.out.println("\nBefore commit:\n\n");
        database.commitTransaction();
        System.out.println("\nAfter commit:\n\n");

*/

        Person person = getPersonById(database, new Long(94));
        System.out.println("\nFirstName: "+ person.getFirstName() + " LastName: " + person.getLastName());


        int count = 0;
        List<Person> allPersons = database.getList("FROM Person", Person.class); // Achtung ist case-sensitive
        for(Person currentPerson  : allPersons) {
            System.out.println(currentPerson.getFirstName() + ", " + currentPerson.getLastName());
            ++count;
        }
        System.out.println("\nAnzahl aller Personen: " + count);



        database.commitTransaction();
        database.endConnection();
    }

    public static Person getPersonById(DBConnection database, Long id) {
        EntityManager em = database.getEntityManager();
        Person result = em.find(Person.class, id);
        return result;
    }
}
