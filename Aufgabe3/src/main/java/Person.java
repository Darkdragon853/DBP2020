import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "creationDate", nullable = false)
    private Timestamp creationDate;

    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 100)
    private String lastName;

    @Column(name = "gender", nullable = false, length = 7)
    private String gender;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

/* Momentane Implementierung lagert das komplett aus!
    @OneToMany
    @JoinTable(name = "person_has_email") // <- Läuft das?
    private List<String> emails;

    @OneToMany
    @JoinTable(name = "person_speaks_language") // <- Läuft das?
    private List<String> speaks;
*/

    @Column(name = "browserUsed", nullable = false, length = 50)
    private String browserUsed;

    @Column(name = "locationIP", nullable = false, length = 40)
    private String locationIP;

    @ManyToOne
    private City city;

    @OneToMany(mappedBy = "moderator")
    private List<Forum> moderatedForums;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @OneToMany(mappedBy = "member")
    private List<Forum_hasMember_Person> relatedForums;



    @OneToMany(mappedBy = "person1")
    private List<Person_knows_Person> persons1;

    @OneToMany(mappedBy = "person2")
    private List<Person_knows_Person> persons2;


    /*
    @ManyToMany
    @JoinTable(
            name = "Person_knows_Person",
            joinColumns = { @JoinColumn(name = "person_1_id")},
            inverseJoinColumns = { @JoinColumn(name = "person_2_id")}
    )
    private List<Person> knownPersons;
    */




    @OneToMany(mappedBy = "person")
    private List<Person_studyAt_University> universities;

    /*@ManyToMany
    @JoinTable(
            name = "Person_studyAt_University",
            joinColumns = { @JoinColumn(name = "person_id")},
            inverseJoinColumns = { @JoinColumn(name = "university_id")}
    )
    private List<University> universities;
    */



    @OneToMany(mappedBy = "person")
    private List<Person_workAt_Company> companies;
    /*@ManyToMany
    @JoinTable(
            name = "Person_workAt_Company",
            joinColumns = { @JoinColumn(name = "person_id")},
            inverseJoinColumns = { @JoinColumn(name = "company_id")}
    )
    private List<Company> companies;
    */


    @OneToMany(mappedBy = "person")
    private List<Person_likes_Post> likedPosts;
    /*
    @ManyToMany
    @JoinTable(
            name = "Person_likesPost_Post",
            joinColumns = { @JoinColumn(name = "person_id")},
            inverseJoinColumns = { @JoinColumn(name = "post_id")}
    )
    private List<Post> likedPosts;
    */

    @OneToMany(mappedBy = "person")
    private List<Person_likes_Comment> likedComments;
    /*
    @ManyToMany
    @JoinTable(
            name = "Person_likesComment_Comment",
            joinColumns = { @JoinColumn(name = "person_id")},
            inverseJoinColumns = { @JoinColumn(name = "comment_id")}
    )
    private List<Comment> likedComments;
    */



    @ManyToMany
    @JoinTable(
            name = "Person_hasInterest_Tag",
            joinColumns = { @JoinColumn(name = "person_id")},
            inverseJoinColumns = { @JoinColumn(name = "tag_id")}
    )
    private List<Tag> interestedTags;





    @OneToMany(mappedBy = "owner")
    private List<Email> emails;

    @ManyToMany
    @JoinTable(
            name = "Person_speaks_Language",
            joinColumns = { @JoinColumn(name = "person_id")},
            inverseJoinColumns = { @JoinColumn(name = "language_id")}
    )
    private List<Language> languages;


























    // Constraints fehlen noch.



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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Forum> getModeratedForums() {
        return moderatedForums;
    }

    public void setModeratedForums(List<Forum> moderatedForums) {
        this.moderatedForums = moderatedForums;
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

    public List<Tag> getInterestedTags() {
        return interestedTags;
    }

    public void setInterestedTags(List<Tag> interestedTags) {
        this.interestedTags = interestedTags;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Forum_hasMember_Person> getRelatedForums() {
        return relatedForums;
    }

    public void setRelatedForums(List<Forum_hasMember_Person> relatedForums) {
        this.relatedForums = relatedForums;
    }

    public List<Person_knows_Person> getPersons1() {
        return persons1;
    }

    public void setPersons1(List<Person_knows_Person> persons1) {
        this.persons1 = persons1;
    }

    public List<Person_knows_Person> getPersons2() {
        return persons2;
    }

    public void setPersons2(List<Person_knows_Person> persons2) {
        this.persons2 = persons2;
    }

    public List<Person_studyAt_University> getUniversities() {
        return universities;
    }

    public void setUniversities(List<Person_studyAt_University> universities) {
        this.universities = universities;
    }

    public List<Person_workAt_Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Person_workAt_Company> companies) {
        this.companies = companies;
    }

    public List<Person_likes_Post> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(List<Person_likes_Post> likedPosts) {
        this.likedPosts = likedPosts;
    }

    public List<Person_likes_Comment> getLikedComments() {
        return likedComments;
    }

    public void setLikedComments(List<Person_likes_Comment> likedComments) {
        this.likedComments = likedComments;
    }
}
