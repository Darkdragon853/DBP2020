import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Post reply_to_post;

    @OneToOne
    private Comment reply_to_comment;

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

    public Post getReply_to_post() {
        return reply_to_post;
    }

    public void setReply_to_post(Post reply_to_post) {
        this.reply_to_post = reply_to_post;
    }

    public Comment getReply_to_comment() {
        return reply_to_comment;
    }

    public void setReply_to_comment(Comment reply_to_comment) {
        this.reply_to_comment = reply_to_comment;
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
