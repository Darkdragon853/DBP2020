import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "forum")
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forumid_generator")
    @SequenceGenerator(name = "forumid_generator", sequenceName = "forumid_seq", allocationSize = 10)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "creationDate", nullable = false)
    private Timestamp creationDate;

    @ManyToOne
    private Person moderator;

    @OneToMany(mappedBy = "forum")
    private List<Post> posts;

    @OneToMany(mappedBy = "forum")
    private List<Forum_hasMember_Person> relatePersons;

    @ManyToMany
    @JoinTable(
            name = "Forum_hasTag_Tag",
            joinColumns = { @JoinColumn(name = "forum_id")},
            inverseJoinColumns = { @JoinColumn(name = "tag_id")}
            )
    private List<Tag> tags;

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Person getModerator() {
        return moderator;
    }

    public void setModerator(Person moderator) {
        this.moderator = moderator;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Forum_hasMember_Person> getRelatePersons() {
        return relatePersons;
    }

    public void setRelatePersons(List<Forum_hasMember_Person> relatePersons) {
        this.relatePersons = relatePersons;
    }
}
