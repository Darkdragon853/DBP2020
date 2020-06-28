import javax.persistence.*;
import java.sql.Date;

@Entity
public class Person_likes_Comment  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personLikesCommentid_generator")
    @SequenceGenerator(name = "personLikesCommentid_generator", sequenceName = "personLikesCommentid_seq", allocationSize = 10)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Column(name = "creationDate")
    private Date creationDate;


    // Getter and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
