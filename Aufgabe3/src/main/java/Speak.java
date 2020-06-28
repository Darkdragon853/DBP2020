import javax.persistence.*;

@Entity
public class Speak {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "speakid_generator")
    @SequenceGenerator(name = "speakid_generator", sequenceName = "speakid_seq", allocationSize = 10)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "language")
    private String language;

    @ManyToOne
    private Person speaker;


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

    public Person getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Person speaker) {
        this.speaker = speaker;
    }
}
