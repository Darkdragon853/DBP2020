import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organisationid_generator")
    @SequenceGenerator(name = "organisationid_generator", sequenceName = "organisationid_seq", allocationSize = 10)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
