import javax.persistence.*;
import java.sql.Date;

@Entity
public class Forum_hasMember_Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forumHasMemberPersonid_generator")
    @SequenceGenerator(name = "forumHasMemberPersonid_generator", sequenceName = "forumHasMemberPersonid_seq", allocationSize = 10)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "forum_id")
    private Forum forum;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person member;

    @Column(name = "joinDate")
    private Date joinDate;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public Person getMember() {
        return member;
    }

    public void setMember(Person member) {
        this.member = member;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}
