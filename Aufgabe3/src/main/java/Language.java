import javax.persistence.*;
import java.util.List;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "language", unique = true)
    private String language;

    @ManyToMany(mappedBy = "languages")
    private List<Person> speakers;


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Person> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Person> speakers) {
        this.speakers = speakers;
    }
}
