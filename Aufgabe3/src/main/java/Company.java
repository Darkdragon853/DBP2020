import javax.persistence.*;
import java.util.List;

@Entity
public class Company extends Organisation {

    @Column(name = "url")
    private String url;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "company")
    private List<Person_workAt_Company> persons;


    /*@ManyToMany(mappedBy = "companies")
    private List<Person> workers;
    */
    // Getter und Setter
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Person_workAt_Company> getPersons() {
        return persons;
    }

    public void setPersons(List<Person_workAt_Company> persons) {
        this.persons = persons;
    }
}
