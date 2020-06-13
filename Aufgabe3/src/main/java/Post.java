import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "id")
    private int id;

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

    //ForeignKey
    @Column(name = "forum_id")
    private int forum_id;

    //ForeignKey
    @Column(name = "author_id")
    private int author_id;

    //ForeignKey
    @Column(name = "country_id")
    private int country_id;

    // Getter und Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }
}