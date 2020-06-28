import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commentid_generator")
    @SequenceGenerator(name = "commentid_generator", sequenceName = "commentid_seq", allocationSize = 10)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "creationDate", nullable = false)
    private Timestamp creationDate;

    @Column(name = "browserUsed", nullable = false, length = 50)
    private String browserUsed;

    @Column(name = "locationIP", nullable = false, length = 40)
    private String locationIP;

    @Column(name = "content")
    private String content;

    @Column(name = "length", nullable = false)
    private int length;

    @ManyToOne
    private Person author;

    @ManyToOne
    private Country country;

    @OneToOne
    private Post repliedPost;

    @OneToOne
    private Comment repliedComment;

    @ManyToMany
    @JoinTable(
            name = "Comment_hasTag_Tag",
            joinColumns = { @JoinColumn(name = "comment_id")},
            inverseJoinColumns = { @JoinColumn(name = "tag_id")}
    )
    private List<Tag> tags;



    @OneToMany(mappedBy = "comment")
    private List<Person_likes_Comment> persons;
    /*
    @ManyToMany(mappedBy = "likedComments")
    private List<Person> likers;
    */




    // Constraints fehlen noch


    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getBrowserUsed() {
        return browserUsed;
    }

    public void setBrowserUsed(String browserUsed) {
        this.browserUsed = browserUsed;
    }

    public String getLocationIP() {
        return locationIP;
    }

    public void setLocationIP(String locationIP) {
        this.locationIP = locationIP;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Post getRepliedPost() {
        return repliedPost;
    }

    public void setRepliedPost(Post repliedPost) {
        this.repliedPost = repliedPost;
    }

    public Comment getRepliedComment() {
        return repliedComment;
    }

    public void setRepliedComment(Comment repliedComment) {
        this.repliedComment = repliedComment;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Person_likes_Comment> getPersons() {
        return persons;
    }

    public void setPersons(List<Person_likes_Comment> persons) {
        this.persons = persons;
    }
}
