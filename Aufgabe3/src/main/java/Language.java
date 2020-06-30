import javax.persistence.*;
import java.util.List;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "speakid_generator")
    @SequenceGenerator(name = "speakid_generator", sequenceName = "speakid_seq", allocationSize = 10)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "language")
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
