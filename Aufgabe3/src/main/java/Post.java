import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "language", length = 2)
    private String language;

    @Column(name = "imageFile", length = 150)
    private String imageFile;

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
    private Forum forum;

    @ManyToOne
    private Person author;

    @ManyToOne
    private Country country;

    @ManyToMany
    @JoinTable(
            name = "Post_hasTag_Tag",
            joinColumns = { @JoinColumn(name = "post_id")},
            inverseJoinColumns = { @JoinColumn(name = "tag_id")}
    )
    private List<Tag> tags;


    @OneToMany(mappedBy = "post")
    private List<Person_likes_Post> persons;
    /*
    @ManyToMany(mappedBy = "likedPosts")
    private List<Person> likers;
    */



    // Getter und Setter
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

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
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

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Person_likes_Post> getPersons() {
        return persons;
    }

    public void setPersons(List<Person_likes_Post> persons) {
        this.persons = persons;
    }
}