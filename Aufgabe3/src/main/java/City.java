import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
public class City extends Place {

    @Column(name = "url")
    private String url;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "city")
    private List<Person> persons;

    @OneToMany(mappedBy = "city")
    private List<University> universities;

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

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<University> getUniversities() {
        return universities;
    }

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }
}
