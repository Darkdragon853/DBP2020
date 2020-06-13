import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "id")
    private int id;

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
    @Column(name = "author_id")
    private int author_id;

    //ForeignKey
    @Column(name = "country_id")
    private int country_id;

    //ForeignKey
    @Column(name = "reply_to_post_id")
    private int reply_to_post_id;

    //ForeignKey
    @Column(name = "reply_to_comment_id")
    private int reply_to_comment_id;

    // Constraints fehlen noch


    // Getter und Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getReply_to_post_id() {
        return reply_to_post_id;
    }

    public void setReply_to_post_id(int reply_to_post_id) {
        this.reply_to_post_id = reply_to_post_id;
    }

    public int getReply_to_comment_id() {
        return reply_to_comment_id;
    }

    public void setReply_to_comment_id(int reply_to_comment_id) {
        this.reply_to_comment_id = reply_to_comment_id;
    }
}
