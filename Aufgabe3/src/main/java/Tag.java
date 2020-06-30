import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "url")
    private String url;

    @ManyToMany(mappedBy = "tags")
    private List<Forum> forums;

    @ManyToMany
    @JoinTable(
            name = "Tag_hasType_TagClass",
            joinColumns = { @JoinColumn(name = "tag_id")},
            inverseJoinColumns = { @JoinColumn(name = "tagclass_id")}
            )
    private List<TagClass> tagclasses;

    @ManyToMany(mappedBy = "tags")
    private List<Post> posts;

    @ManyToMany(mappedBy = "tags")
    private List<Comment> comments;

    @ManyToMany(mappedBy = "interestedTags")
    private List<Person> interestedPersons;


    // Getter and Setters
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Forum> getForums() {
        return forums;
    }

    public void setForums(List<Forum> forums) {
        this.forums = forums;
    }

    public List<TagClass> getTagclasses() {
        return tagclasses;
    }

    public void setTagclasses(List<TagClass> tagclasses) {
        this.tagclasses = tagclasses;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Person> getInterestedPersons() {
        return interestedPersons;
    }

    public void setInterestedPersons(List<Person> interestedPersons) {
        this.interestedPersons = interestedPersons;
    }
}
