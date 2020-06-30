import javax.persistence.*;
import java.util.List;

@Entity
public class University extends Organisation {

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne
    private City city;

    @OneToMany(mappedBy = "university")
    private List<Person_studyAt_University> students;

    /*@ManyToMany(mappedBy = "universities")
    private List<Person> students;
    */

    // Getter und Setter
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Person_studyAt_University> getStudents() {
        return students;
    }

    public void setStudents(List<Person_studyAt_University> students) {
        this.students = students;
    }
}
