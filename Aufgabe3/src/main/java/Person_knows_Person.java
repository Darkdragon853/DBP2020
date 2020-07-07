import javax.persistence.*;
import java.sql.Date;

@Entity
public class Person_knows_Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_1_id")
    private Person person1;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_2_id")
    private Person person2;

    @Column(name = "creationDate", nullable = false)
    private Date creationDate;


}
